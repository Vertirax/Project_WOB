/*
 * This file was automatically generated by EvoSuite
 * Sat Jun 01 19:14:38 GMT 2019
 */

import com.krizsanandras.projectwob.Listing;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Listing_ESTest extends Listing_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setUpload_timeString("8M=^|k1p+q&]");
      String string0 = listing0.getUpload_timeString();
      assertEquals("8M=^|k1p+q&]", string0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setUpload_timeString("");
      String string0 = listing0.getUpload_timeString();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setTitle("bR{@9>L8)E@");
      String string0 = listing0.getTitle();
      assertEquals("bR{@9>L8)E@", string0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setTitle("");
      String string0 = listing0.getTitle();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setQuantity(1);
      int int0 = listing0.getQuantity();
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setQuantity((-1600));
      int int0 = listing0.getQuantity();
      assertEquals((-1600), int0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setOwner_email_address("com.krizsanandras.projectwob.Listing");
      String string0 = listing0.getOwner_email_address();
      assertEquals("com.krizsanandras.projectwob.Listing", string0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setOwner_email_address("");
      String string0 = listing0.getOwner_email_address();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setMarketplace(1895);
      int int0 = listing0.getMarketplace();
      assertEquals(1895, int0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setMarketplace((-4153));
      int int0 = listing0.getMarketplace();
      assertEquals((-4153), int0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setLocation_id("o._]");
      String string0 = listing0.getLocation_id();
      assertEquals("o._]", string0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setListing_status(2480);
      int int0 = listing0.getListing_status();
      assertEquals(2480, int0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setListing_status((-1));
      int int0 = listing0.getListing_status();
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setListing_price(2897.0);
      double double0 = listing0.getListing_price();
      assertEquals(2897.0, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setListing_price((-330.944));
      double double0 = listing0.getListing_price();
      assertEquals((-330.944), double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setId("]#");
      String string0 = listing0.getId();
      assertEquals("]#", string0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setDescription("com.krizsanandras.projectwob.Listing");
      String string0 = listing0.getDescription();
      assertEquals("com.krizsanandras.projectwob.Listing", string0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setDescription("");
      String string0 = listing0.getDescription();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setCurrency("E8J");
      String string0 = listing0.getCurrency();
      assertEquals("E8J", string0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setCurrency("");
      String string0 = listing0.getCurrency();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Listing listing0 = new Listing();
      String string0 = listing0.getLocation_id();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      Listing listing0 = new Listing();
      int int0 = listing0.getMarketplace();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      Listing listing0 = new Listing();
      String string0 = listing0.getUpload_timeString();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      Listing listing0 = new Listing();
      String string0 = listing0.getOwner_email_address();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setLocation_id("");
      String string0 = listing0.getLocation_id();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      Listing listing0 = new Listing();
      listing0.setId("");
      String string0 = listing0.getId();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      Listing listing0 = new Listing();
      String string0 = listing0.getDescription();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      Listing listing0 = new Listing();
      String string0 = listing0.getTitle();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      Listing listing0 = new Listing();
      String string0 = listing0.getId();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      Listing listing0 = new Listing();
      int int0 = listing0.getListing_status();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      Listing listing0 = new Listing();
      int int0 = listing0.getQuantity();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      Listing listing0 = new Listing();
      double double0 = listing0.getListing_price();
      assertEquals(0.0, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      Listing listing0 = new Listing();
      String string0 = listing0.getCurrency();
      assertNull(string0);
  }
}