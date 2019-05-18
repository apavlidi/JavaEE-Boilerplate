package com.apavlidi.batch;

import java.util.Date;
import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.StepContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("LogListener")
public class LogListener implements StepListener {

  @Inject
  private StepContext stepContext;

  private Date dateStart;

  @Override
  public void beforeStep() throws Exception {
    dateStart = new Date();
    System.out.println("Start step: " + stepContext.getStepName());
    System.out.println("Timer task started at: " + dateStart);
  }

  @Override
  public void afterStep() throws Exception {
    Date dateEnd = new Date();
    System.out.println("End step: " + stepContext.getStepName());
    System.out.println("Timer task ended at: " + dateEnd);
    System.out.println("Total time: " + (dateEnd.getTime() - dateStart.getTime() + " milliseconds"));
  }
}
