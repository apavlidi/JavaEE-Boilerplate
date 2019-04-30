package com.apavlidi.batch;


import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

public class BatchTestHelper {

  private static final int MAX_TRIES = 40;
  private static final int THREAD_SLEEP = 1000;

  private BatchTestHelper() {
    throw new UnsupportedOperationException();
  }

  public static JobExecution keepTestAlive(JobExecution jobExecution) throws InterruptedException {
    int maxTries = 0;
    while (!jobExecution.getBatchStatus().equals(BatchStatus.COMPLETED)) {
      if (maxTries < MAX_TRIES) {
        maxTries++;
        Thread.sleep(THREAD_SLEEP);
        jobExecution = BatchRuntime.getJobOperator()
            .getJobExecution(jobExecution.getExecutionId());
      } else {
        break;
      }
    }
    Thread.sleep(THREAD_SLEEP);
    return jobExecution;
  }

}