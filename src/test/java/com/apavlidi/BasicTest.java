package com.apavlidi;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class BasicTest {

  @Deployment
  public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }


  @Test
  public void login_mary() {
    Assert.assertEquals(1, 1);
    System.out.println("======================");
    System.out.println("======================");
    System.out.println("======================");
    System.out.println("HELLO");
    System.out.println("======================");
    System.out.println("======================");
    System.out.println("======================");

  }
}