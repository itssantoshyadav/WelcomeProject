package testNgTesting;

import org.testng.annotations.Test;

public class Sample1 {
  @Test(groups="Three")
  public void method11() {
	  Long id= Thread.currentThread().getId();
	  System.out.println("Hello method 11 " +id);
  }
  @Test(groups= {"Regression","Three"})
  public void method12() {
	  Long id= Thread.currentThread().getId();
	  System.out.println("Hello method 12 "+id);
  }
  @Test(groups="Four")
  public void method14() {
	  Long id= Thread.currentThread().getId();
	  System.out.println("Hello method 14 "+id);
  }
  @Test(groups="Four")
  public void method13() {
	  Long id= Thread.currentThread().getId();
	  System.out.println("Hello method 13 " +id);
  }
}
