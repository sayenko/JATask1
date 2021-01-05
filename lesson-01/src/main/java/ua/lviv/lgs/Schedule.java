package ua.lviv.lgs;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {
    private Set<Seance> seances = new TreeSet<>();

    public void addSeance (Seance seance){
        this.seances.add(seance);
    }

    public void removeSeance (Seance seance){
        this.seances.remove(seance);
    }

    public Set<Seance> getSeances() {
        return seances;
    }

    public void setSeances(Set<Seance> seances) {
        this.seances = seances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return getSeances().equals(schedule.getSeances());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSeances());
    }

    @Override
    public String toString() {
        return "Schedule: " + seances;
    }
}
