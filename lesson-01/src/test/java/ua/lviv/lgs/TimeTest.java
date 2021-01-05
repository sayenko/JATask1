package ua.lviv.lgs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class TimeTest {
	
	private Time actualTime;

	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		protected void failed(Throwable e, org.junit.runner.Description description) {
			System.out.println("FAILED--> " + description.getMethodName());
		};

		protected void succeeded(org.junit.runner.Description description) {
			System.out.println("SUCCEED--> " + description.getMethodName());
		};
	};

	@Before
	public void beforeTest() {
		actualTime = new Time(0);
	}

	@After
	public void afterTest() {
		actualTime = null;
	}
	
	@Test
	public void getDurationTest() {
		actualTime = new Time(1, 40);
		Time expectedTime = new Time(100);
		Assert.assertEquals(expectedTime, actualTime);
	}

	@Test(expected = Error.class)
	public void getDurationWithExceptionTest() {
		actualTime = new Time("one", "forty");
		Time expectedTime = new Time(100);
		Assert.assertEquals(expectedTime, actualTime);
	}
	
	@Test
	public void setMinTest() {
		actualTime.setMin(84);
		int actualMin = actualTime.getMin();
		int expectedMin = 24;
		Assert.assertEquals(expectedMin, actualMin);
	}
	
	@Test
	public void setHourTest() {
		actualTime.setHour(54);
		int actualHour = actualTime.getHour();
		int expectedHour = 6;
		Assert.assertEquals(expectedHour, actualHour);
	}	
}
