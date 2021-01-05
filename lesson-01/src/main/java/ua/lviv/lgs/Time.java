package ua.lviv.lgs;

import java.util.Objects;

public class Time {
    private int hour;
    private int min;

    public Time(int min) {
        if (min < 0) {
            min *= (-1);
        }
        this.hour = (min/60)%24;
        this.min = min % 60;
    }

    public Time(int hour, int min) {
        if (hour < 0) {
            hour *= (-1);
        }
        if (min < 0) {
            min *= (-1);
        }
        if(min>59) {
        	hour++;
        }
        this.hour = hour % 24;
        this.min = min % 60;
    }

    public int getDuration() {
    	return hour * 60 + min;
    }
    
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        if (min < 0) {
            min *= (-1);
        }
        this.min = min % 60;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if (hour < 0) {
            hour *= (-1);
        }
        this.hour = hour % 24;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return getMin() == time.getMin() &&
                getHour() == time.getHour();
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, hour);
    }

    @Override
    public String toString() {
    	if(min < 10) return "Time: " + hour + ":0" + min;
    	else return "Time: " + hour + ":" + min;
    }
}
