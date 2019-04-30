package com.apavlidi.batch;

import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SimpleChunkItemReader extends AbstractItemReader {

  private Integer[] tokens;
  private Integer count;

  @Inject
  JobContext jobContext;

  @Override
  public Integer readItem() throws Exception {
    if (count >= tokens.length) {
      return null;
    }

    jobContext.setTransientUserData(count);
    return tokens[count++];
  }

  @Override
  public void open(Serializable checkpoint) throws Exception {
    tokens = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    count = 0;
  }

}