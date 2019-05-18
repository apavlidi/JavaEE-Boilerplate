package com.apavlidi.batch;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.BatchStatus;
import javax.inject.Named;

@Named
public class SimpleBatchLet extends AbstractBatchlet {

  @Override
  public String process() throws Exception {
    System.out.println("==Batchlet Step Process==");
    return BatchStatus.COMPLETED.toString();
  }
}