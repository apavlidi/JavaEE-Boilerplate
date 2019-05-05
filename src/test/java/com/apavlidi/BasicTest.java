package com.apavlidi;

import static org.junit.Assert.assertEquals;

import com.apavlidi.api.exceptions.DataNotFoundException;
import com.apavlidi.batch.BatchTestHelper;
import com.apavlidi.batch.SimpleChunkItemReader;
import com.apavlidi.domain.Note;
import com.apavlidi.repository.NoteRepository;
import com.apavlidi.service.NoteService;
import com.apavlidi.util.EntityManagerProducer;
import java.io.File;
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
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class BasicTest {

  @Deployment
  public static WebArchive createDeployment() {

    File[] pomDependencies = getPomDependencies();

    return ShrinkWrap.create(WebArchive.class)
        .addClass(Note.class)
        .addClass(EntityManager.class)
        .addPackages(false,
            NoteService.class.getPackage(),
            NoteRepository.class.getPackage(),
            DataNotFoundException.class.getPackage(),
            Note.class.getPackage(),
            BatchTestHelper.class.getPackage(),
            SimpleChunkItemReader.class.getPackage(),
            EntityManagerProducer.class.getPackage())
        .addAsResource("META-INF/persistence.xml")
        .addAsResource("META-INF/batch-jobs/simpleChunk.xml")
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
  private NoteService noteService2;

  @Test
  public void login_mary() {
    assertEquals(1, 1);
    System.out.println("======================");
    System.out.println("======================");
    System.out.println("======================");
    System.out.println("HELLO");
    System.out.println("======================");
    System.out.println("======================");
    System.out.println("======================");
  }

  @Test
  public void persist() {
    Note note = new Note();
    note.setText("TESTING FROM ARQUILLIAN WITH EM");
    noteService2.persist(note);
  }

  @Test
  public void givenChunk_thenBatch_completesWithSuccess() throws Exception {
    JobOperator jobOperator = BatchRuntime.getJobOperator();
    Long executionId = jobOperator.start("simpleChunk", new Properties());
    JobExecution jobExecution = jobOperator.getJobExecution(executionId);
    jobExecution = BatchTestHelper.keepTestAlive(jobExecution);
    assertEquals(jobExecution.getBatchStatus(), BatchStatus.COMPLETED);
  }
}