package ua.lviv.lgs;

import java.util.Objects;

public class Movie {
    private String title;
    private Time duration;

    public Movie(String title, Time duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return getTitle().equals(movie.getTitle()) &&
                getDuration().equals(movie.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDuration());
    }

    @Override
    public String toString() {    	
        return "Movie \"" + title + "\", duration: " + duration.getDuration();
    }
}
