package ua.lviv.lgs;

import java.util.*;
import java.util.function.Consumer;

public class Main {

	public static void main(String[] args) {
		Cinema cinema = new Cinema(new Time(10, 00), new Time(23, 00));

		while (true) {
			System.out.println("Оберіть пункт меню:");
			System.out.println("1. Додати фільм в фільмотеку");
			System.out.println("2. Додати сеанс фільму в розклад");
			System.out.println("3. Видалити фільм з фільмотеки і розкладу");
			System.out.println("4. Видалити конкретний сеанс фільму");
			System.out.println("5. Показати Cinema");
			System.out.println("6. Вийти з програми");
			try {
				menu(cinema);
			} catch (Exception e) {
				break;
			}
		}
	}

	public static void menu(Cinema cinema) {
		Scanner scanner = new Scanner(System.in);
		CinemaMenuInterface cm1 = Main::addMovie;
		CinemaMenuInterface cm2 = Main::addSeance;
		CinemaMenuInterface cm3 = Main::removeMovie;
		CinemaMenuInterface cm4 = Main::removeSeance;
		Consumer<Cinema> cm5 = Main::showCinema;
		Consumer<Scanner> cm6 = Main::exit;

		switch (scanner.nextLine()) {
		case "1":
			cm1.menu(cinema, scanner);
			break;
		case "2":
			cm2.menu(cinema, scanner);
			break;
		case "3":
			cm3.menu(cinema, scanner);
			break;
		case "4":
			cm4.menu(cinema, scanner);
			break;
		case "5":
			cm5.accept(cinema);
			break;
		case "6":
			cm6.accept(scanner);
			break;
		}
	}

	public static void showCinema(Cinema cinema) {
        System.out.println(cinema);
    }

    public static void addMovie(Cinema cinema, Scanner scanner) {
        System.out.println("Додати фільм в фільмотеку moviesLibrary");
        System.out.println("Введіть назву фільму");
        String movieName = scanner.nextLine();
        int duration;
        while (true) {
            System.out.println("Введіть тривалість фільму в хвилинах");
            try {
                duration = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Введена не коректна тривалість фільму");
                scanner.next();
            }
        }
        cinema.addMovie(new Movie(movieName, new Time(duration)));
    }

    public static void addSeance(Cinema cinema, Scanner scanner) {
        System.out.println("Додати сеанс фільму в розклад");
        String day = null;

        while (true) {
            System.out.println("Введіть день сеансу");
            try {
                day = scanner.nextLine();
                String dayStream = day.toUpperCase();                
                if(Arrays.stream(Days.values()).anyMatch(d -> d.toString().equals(dayStream))){
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введений не корректний день тижня");
                scanner.next();
            }
        }

        List<Movie> movies = cinema.getMoviesLibrary();
        System.out.println("Введіть назву фільму");
        String movieName = scanner.nextLine();
        while (movies.stream().noneMatch(movie -> movieName.equalsIgnoreCase(movie.getTitle()))) {
            System.out.println("Такий фільм ще не додан у фільмотеку. Додати? (Yes/No)");
            String addMovieToLibrary = scanner.nextLine();
            if (addMovieToLibrary.equalsIgnoreCase("Yes")) {
                addMovie(cinema, scanner);
            } else return;
        }

        int hour;
        while (true) {
            System.out.println("Введіть години початку сеансу");
            try {
                hour = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Введена не коректна година початку сеансу");
                scanner.next();
            }
        }

        int min;
        while (true) {
            System.out.println("Введіть хвилини початку сеансу");
            try {
                min = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Введені не коректні хвилини початку сеансу");
                scanner.next();
            }
        }

        int hourStream = hour;
        int minStream = min;
        String dayStream = day;
        movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(movieName))
                .findFirst()
                .ifPresent(movie -> cinema.addSeance(new Seance(movie, new Time(hourStream, minStream)), dayStream));
    }

    public static void removeMovie(Cinema cinema, Scanner scanner) {
        System.out.println("Видалити фільм із moviesLibrary");
        System.out.println("Введіть назву фільму");
        String movieName = scanner.nextLine();

        int duration;
        while (true) {
            System.out.println("Введіть тривалість фільму в хвилинах");
            try {
                duration = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Введена не коректна тривалість фільму");
                scanner.next();
            }
        }
        cinema.removeMovie(new Movie(movieName, new Time(duration)));
    }

    public static void removeSeance(Cinema cinema, Scanner scanner) {
        System.out.println("Видалити фільм із сеансу");
        String day = null;

        while (true) {
            System.out.println("Введіть день сеансу");
            try {
                day = scanner.nextLine();
                String dayStream = day.toUpperCase();
                if(Arrays.stream(Days.values()).anyMatch(d -> d.toString().equals(dayStream))){
                    break;
                }
            } catch (Exception e) {
                System.out.println("Введений не корректний день тижня");
                scanner.next();
            }
        }

        List<Movie> movies = cinema.getMoviesLibrary();
        System.out.println("Введіть назву фільму");
        String movieName = scanner.nextLine();
        if(movies.stream().noneMatch(movie -> movie.getTitle().equalsIgnoreCase(movieName))) {
            System.out.println("Фільма з такою назвою не знайдено");
            return;
        }

        int hour;
        while (true) {
            System.out.println("Введіть години початку сеансу");
            try {
                hour = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Введена не коректна година початку сеансу");
                scanner.next();
            }
        }

        int min;
        while (true) {
            System.out.println("Введіть хвилини початку сеансу");
            try {
                min = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Введені не коректні хвилини початку сеансу");
                scanner.next();
            }
        }

        int hourStream = hour;
        int minStream = min;
        String dayStream = day;
        movies.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(movieName))
                .findFirst()
                .ifPresent(movie -> cinema.removeSeance(new Seance(movie, new Time(hourStream, minStream)), dayStream));
    }

    public static void exit(Scanner scanner) {
        scanner.close();
        System.exit(0);
    }
}

interface CinemaMenuInterface {
    void menu(Cinema cinema, Scanner scanner);
}
