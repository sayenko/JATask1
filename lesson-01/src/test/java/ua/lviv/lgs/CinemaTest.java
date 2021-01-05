package ua.lviv.lgs;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class CinemaTest {
	private Cinema actualCinema;
	private Cinema expectedCinema;

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
		actualCinema = new Cinema(new Time(10, 00), new Time(23, 00));
		expectedCinema = new Cinema(new Time(10, 00), new Time(23, 00));
	}

	@After
	public void afterTest() {
		actualCinema = null;
		expectedCinema = null;
	}

	@Test
	public void addMovieTest() {
		// actual value
		actualCinema.addMovie(new Movie("Titanic", new Time(120)));
		ArrayList<Movie> actualMoviesLibrary = actualCinema.getMoviesLibrary();

		// expected value
		ArrayList<Movie> expectedMoviesLibrary = expectedCinema.getMoviesLibrary();
		expectedMoviesLibrary.add(new Movie("Titanic", new Time(120)));

		// result
		Assert.assertEquals(expectedMoviesLibrary, actualMoviesLibrary);
	}
	
	@Test(expected = Error.class)
	public void addMovieWithExceptionTest() {
		// actual value
		actualCinema.addMovie(new Movie("Titanic", new Time("twelve")));
		ArrayList<Movie> actualMoviesLibrary = actualCinema.getMoviesLibrary();

		// expected value
		ArrayList<Movie> expectedMoviesLibrary = expectedCinema.getMoviesLibrary();
		expectedMoviesLibrary.add(new Movie("Titanic", new Time(120)));

		// result
		Assert.assertEquals(expectedMoviesLibrary, actualMoviesLibrary);
	}

	@Test
	public void addSeanceTest() {
		// actual value
		actualCinema.addSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)), "TUESDAY");
		TreeMap<Days, Schedule> actualSchedules = actualCinema.getSchedules();

		// expected value
		Days day = Days.valueOf("TUESDAY".toUpperCase());
		TreeMap<Days, Schedule> expectedSchedules = new TreeMap<Days, Schedule>();
		Schedule schedule = new Schedule();
		schedule.addSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)));
		expectedSchedules.put(day, schedule);

		// result
		Assert.assertEquals(expectedSchedules, actualSchedules);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addSeanceWithExceptionTest() {
		// actual value
		actualCinema.addSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)), "VTORNIK");
		TreeMap<Days, Schedule> actualSchedules = actualCinema.getSchedules();

		// expected value
		Days day = Days.valueOf("TUESDAY".toUpperCase());
		TreeMap<Days, Schedule> expectedSchedules = new TreeMap<Days, Schedule>();
		Schedule schedule = new Schedule();
		schedule.addSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)));
		expectedSchedules.put(day, schedule);

		// result
		Assert.assertEquals(expectedSchedules, actualSchedules);
	}

	@Test
	public void removeMovieTest() {
		// actual value
		actualCinema.addMovie(new Movie("Titanic", new Time(120))); // stupidly
		actualCinema.removeMovie(new Movie("Titanic", new Time(120)));
		ArrayList<Movie> actualMoviesLibrary = actualCinema.getMoviesLibrary();

		// expected value
		ArrayList<Movie> expectedMoviesLibrary = expectedCinema.getMoviesLibrary();
		expectedMoviesLibrary.add(new Movie("Titanic", new Time(120))); // stupidly
		expectedMoviesLibrary.remove(new Movie("Titanic", new Time(120)));

		// result
		Assert.assertEquals(expectedMoviesLibrary, actualMoviesLibrary);
	}

	@Test
	public void removeSeanceTest() {
		// actual value
		actualCinema.addSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)), "TUESDAY");
		actualCinema.removeSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)), "TUESDAY");
		TreeMap<Days, Schedule> actualSchedules = actualCinema.getSchedules();

		// expected value
		Days day = Days.valueOf("TUESDAY".toUpperCase());
		TreeMap<Days, Schedule> expectedSchedules = new TreeMap<Days, Schedule>();
		Schedule schedule = new Schedule();
		schedule.addSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)));
		expectedSchedules.put(day, schedule);
		
		expectedSchedules.entrySet().stream().filter(entry -> entry.getKey().equals(day)).forEach(entry -> {
            Schedule tempSchedule = entry.getValue();
            Set<Seance> seances = tempSchedule.getSeances();
            seances.remove(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)));
            tempSchedule.setSeances(seances);
            entry.setValue(tempSchedule);
        });

		// result
		Assert.assertEquals(expectedSchedules, actualSchedules);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void removeSeanceWithExceptionTest() {
		// actual value
		actualCinema.addSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)), "VTORNIK");
		actualCinema.removeSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)), "TUESDAY");
		TreeMap<Days, Schedule> actualSchedules = actualCinema.getSchedules();

		// expected value
		Days day = Days.valueOf("TUESDAY".toUpperCase());
		TreeMap<Days, Schedule> expectedSchedules = new TreeMap<Days, Schedule>();
		Schedule schedule = new Schedule();
		schedule.addSeance(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)));
		expectedSchedules.put(day, schedule);
		
		expectedSchedules.entrySet().stream().filter(entry -> entry.getKey().equals(day)).forEach(entry -> {
            Schedule tempSchedule = entry.getValue();
            Set<Seance> seances = tempSchedule.getSeances();
            seances.remove(new Seance(new Movie("Titanic", new Time(120)), new Time(14, 30)));
            tempSchedule.setSeances(seances);
            entry.setValue(tempSchedule);
        });

		// result
		Assert.assertEquals(expectedSchedules, actualSchedules);
	}
	
	
}
