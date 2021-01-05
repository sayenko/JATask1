package ua.lviv.lgs;

import java.util.*;

public class Cinema {
    private TreeMap<Days, Schedule> schedules;
    private ArrayList<Movie> moviesLibrary = new ArrayList<>();
    private Time open;
    private Time close;

    public Cinema(Time open, Time close) {
        this.open = open;
        this.close = close;
        this.schedules = new TreeMap<>();
    }

    public void addMovie (Movie movie){
        if(!moviesLibrary.contains(movie)){
            moviesLibrary.add(movie);
        }
    }

    public void addSeance (Seance seance, String day){
        day = day.toUpperCase();
        if(isOpen(seance)) {
            if(!schedules.containsKey(Days.valueOf(day))) {
                schedules.put(Days.valueOf(day.toUpperCase()), new Schedule());
            }
            Schedule schedule = schedules.get(Days.valueOf(day));
            schedule.addSeance(seance);
            schedules.put(Days.valueOf(day), schedule);
        }
    }

    public void removeMovie(Movie movie){
        moviesLibrary.remove(movie);

        schedules.values().stream().map(Schedule::getSeances).forEachOrdered(seances -> {
            seances.removeIf(seance -> seance.getMovie().equals(movie));
        });
    }

    public void removeSeance (Seance seance, String day){
        schedules.entrySet().stream().filter(entry -> entry.getKey().equals(Days.valueOf(day.toUpperCase()))).forEach(entry -> {
            Schedule schedule = entry.getValue();
            Set<Seance> seances = schedule.getSeances();
            seances.remove(seance);
            schedule.setSeances(seances);
            entry.setValue(schedule);
        });
    }

    public TreeMap<Days, Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(TreeMap<Days, Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Movie> getMoviesLibrary() {
        return moviesLibrary;
    }

    public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
        this.moviesLibrary = moviesLibrary;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return getSchedules().equals(cinema.getSchedules()) &&
                getMoviesLibrary().equals(cinema.getMoviesLibrary()) &&
                getOpen().equals(cinema.getOpen()) &&
                getClose().equals(cinema.getClose());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSchedules(), getMoviesLibrary(), getOpen(), getClose());
    }

    @Override
    public String toString() {
        return "Cinema" +
                ", open=" + open +
                ", close=" + close +
                " " + schedules +
                ", moviesLibrary = " + moviesLibrary;
    }

    private boolean isOpen(Seance seance){
        boolean isOpen = (this.open.getHour() < seance.getStartTime().getHour()
                || (this.open.getHour() == seance.getStartTime().getHour() && this.open.getMin() <= seance.getStartTime().getMin()))
                && ((this.close.getHour() > seance.getEndTime().getHour()
                || (this.close.getHour() == seance.getEndTime().getHour() && this.close.getMin() >= seance.getEndTime().getMin())));
        return isOpen;
    }
}