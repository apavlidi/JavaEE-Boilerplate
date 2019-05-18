package com.apavlidi.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class SimpleChunkItemProcessor implements ItemProcessor {

  private static boolean flag = true;

  @Override
  public Integer processItem(Object t) {
    Integer item = (Integer) t;

    if (item == 9) {
      System.out.println("Exception thrown for skip");
      throw new RuntimeException();
    }

    if (flag) {
      flag = false;
      System.out.println("Exception thrown for retry");
      throw new UnsupportedOperationException();
    }

    return item % 2 == 0 ? item : null;
  }

}