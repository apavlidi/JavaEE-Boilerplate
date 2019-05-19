package com.apavlidi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.apavlidi.api.exceptions.DataNotFoundException;
import com.apavlidi.batch.BatchTestHelper;
import com.apavlidi.domain.Note;
import com.apavlidi.repository.NoteRepository;
import com.apavlidi.service.NoteService;
import com.apavlidi.util.EntityManagerProducer;
import java.io.File;
import java.util.List;
import java.util.Properties;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class BasicTest {

  private static final String BATCH_XML_PATH = "src/main/resources/META-INF/batch-jobs/simpleChunk.xml";
  private static Long NOTE_ID;
  private static final String NOTE_TEXT = "test";

  @Deployment
  public static WebArchive createDeployment() {

    File[] pomDependencies = getPomDependencies();
    File batchXml = new File(BATCH_XML_PATH);

    return ShrinkWrap.create(WebArchive.class)
        .addClass(Note.class)
        .addClass(EntityManager.class)
        .addPackages(false,
            NoteService.class.getPackage(),
            NoteRepository.class.getPackage(),
            DataNotFoundException.class.getPackage(),
            Note.class.getPackage(),
            BatchTestHelper.class.getPackage(),
            EntityManagerProducer.class.getPackage())
        .addAsResource("META-INF/persistence.xml")
        .addAsResource(batchXml,
            "META-INF/batch-jobs/simpleChunk.xml")
        .addAsResource("META-INF/beans.xml")
        .addAsLibraries(pomDependencies);
  }

  private static File[] getPomDependencies() {
    return Maven.resolver()
        .loadPomFromFile("pom.xml")
        .importRuntimeDependencies()
        .importTestDependencies()
        .resolve()
        .withTransitivity()
        .asFile();
  }


  @Inject
  private NoteService noteService;

  @Before
  public void setup() {
    Note note = new Note();
    note.setText(NOTE_TEXT);
    NOTE_ID = noteService.persist(note);
  }

  @After
  public void tearDown() {
    try {
      noteService.deleteNoteById(NOTE_ID);
    } catch (DataNotFoundException ignored) {
    }
  }

  @Test
  public void should_persist_note() {
    Note note = new Note();
    note.setText("TESTING FROM ARQUILLIAN WITH EM");
    noteService.persist(note);
  }

  @Test
  public void should_retrieve_all_notes() {
    List<Note> allNotes = noteService.findAllNotes();
    assertNotNull(allNotes);
  }

  @Test
  public void should_retrieve_note_by_text() {
    Note noteByText = noteService.findNoteByText(NOTE_TEXT);
    assertNotNull(noteByText);
    assertEquals(noteByText.getNoteId(), NOTE_ID);
    assertEquals(noteByText.getText(), NOTE_TEXT);
  }

  @Test
  public void should_retrieve_note_by_id() {
    Note noteByText = noteService.findNoteById(NOTE_ID);
    assertNotNull(noteByText);
    assertEquals(noteByText.getNoteId(), NOTE_ID);
    assertEquals(noteByText.getText(), NOTE_TEXT);
  }

  @Test
  @Ignore
  public void should_update_note_by_id() {
    String updatedText = "updated";

    Note updatedNote = new Note();
    updatedNote.setText(updatedText);
    noteService.updateNoteById(NOTE_ID, updatedNote);

    Note updatedNoteById = noteService.findNoteById(NOTE_ID);
    assertNotNull(updatedNoteById);
    assertEquals(updatedNoteById.getNoteId(), NOTE_ID);
    assertEquals(updatedNoteById.getText(), updatedText);
  }

  @Test(expected = DataNotFoundException.class)
  @Ignore
  public void should_delete_note_by_id() {
    noteService.deleteNoteById(NOTE_ID);
    noteService.findNoteById(NOTE_ID);
  }

  @Test
  public void givenChunk_thenBatch_completesWithSuccess() throws Exception {
    JobOperator jobOperator = BatchRuntime.getJobOperator();
    long executionId = jobOperator.start("simpleChunk", new Properties());
    JobExecution jobExecution = jobOperator.getJobExecution(executionId);
    jobExecution = BatchTestHelper.keepTestAlive(jobExecution);
    assertEquals(jobExecution.getBatchStatus(), BatchStatus.COMPLETED);
  }
}