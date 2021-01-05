package ua.lviv.lgs;

import java.util.Objects;

public class Seance implements Comparable<Seance> {
    private Movie movie;
    private Time startTime;
    private Time endTime;

    public Seance(Movie movie, Time startTime) {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime(startTime, movie.getDuration());
    }

    private static Time endTime(Time startTime, Time duration) {
        int endTimeMinut = startTime.getMin() + duration.getMin();
        int endTimeHour = startTime.getHour() + duration.getHour();
        if (endTimeMinut > 59) {
            endTimeHour++;
        }
        return new Time(endTimeHour, endTimeMinut);
    }

    public Movie getMovie() {
        return movie;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        this.endTime = endTime(this.startTime, movie.getDuration());
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
        this.endTime = endTime(startTime, this.movie.getDuration());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return getMovie().equals(seance.getMovie()) &&
                getStartTime().equals(seance.getStartTime()) &&
                getEndTime().equals(seance.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMovie(), getStartTime(), getEndTime());
    }

    @Override
    public String toString() {
        return "Seance: " + movie +
                ", startTime=" + startTime +
                ", endTime=" + endTime;
    }

    @Override
    public int compareTo(Seance o) {
        if (this.getMovie().getTitle().compareTo(o.getMovie().getTitle()) > 0) {
            return 1;
        } else if (this.getMovie().getTitle().compareTo(o.getMovie().getTitle()) < 0) {
            return -1;
        }
        if ((this.getStartTime().getHour() - o.getStartTime().getHour()) > 0) {
            return 1;
        } else {
            if ((this.getStartTime().getHour() - o.getStartTime().getHour()) > 0) {
                return -1;
            } else {
                if ((this.getStartTime().getMin() - o.getStartTime().getMin()) > 0) {
                    return 1;
                } else if ((this.getStartTime().getMin() - o.getStartTime().getMin()) < 0) {
                    return -1;
                }
            }
        }
        return 0;
    }
}
