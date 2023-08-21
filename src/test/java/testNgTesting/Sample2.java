package testNgTesting;

import org.testng.annotations.Test;

public class Sample2 {
  @Test(groups="Two")
  public void method21() {
	  Long id= Thread.currentThread().threadId();
	  System.out.println("Hellot method 21 "+id);
  }
  @Test(groups="Two")
  public void method22() {
	  Long id= Thread.currentThread().threadId();
	  System.out.println("Hellot method 21 "+id);
  }
  @Test(groups="One")
  public void method23() {
	  Long id= Thread.currentThread().threadId();
	  System.out.println("Hellot method 21 "+id);
  }
  @Test(groups="One")
  public void method24() {
	  Long id= Thread.currentThread().threadId();
	  System.out.println("Hellot method 21 "+id);
  }
}
