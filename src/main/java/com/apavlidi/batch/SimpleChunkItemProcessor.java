package com.apavlidi.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class SimpleChunkItemProcessor implements ItemProcessor {

  @Override
  public Integer processItem(Object t) {
    Integer item = (Integer) t;
    return item % 2 == 0 ? item : null;
  }

}