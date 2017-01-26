package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Администратор on 26.01.2017.
 */
public class PointTests {

  @Test
  public void testdistance (){
    Point p1 = new Point(-5, 12);

    Point p2 = new Point(-5, -1);
    Assert.assertEquals(p2.distance(p1), 13.0);
  }
}
