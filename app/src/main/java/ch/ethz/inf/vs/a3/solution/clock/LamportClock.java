package ch.ethz.inf.vs.a3.solution.clock;

import ch.ethz.inf.vs.a3.clock.Clock;

public class LamportClock implements Clock {

    private int time;
    @Override
    public void update(Clock other) {
        // TODO Auto-generated method stub
        int updateTime = ((LamportClock) other).time;
        if (this.time < updateTime) {
            this.time = updateTime;
        }
    }

    @Override
    public void setClock(Clock other) {
        // TODO Auto-generated method stub
        this.time = ((LamportClock)other).time;
    }

    @Override
    public void tick(Integer pid) {
        // TODO Auto-generated method stub
        this.time+=1;
    }

    @Override
    public boolean happenedBefore(Clock other) {
        // TODO Auto-generated method stub
        int updateTime = ((LamportClock) other).time;
        if (this.time < updateTime) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public String toString() {
        return String.valueOf(this.time);
    }

    @Override
    public void setClockFromString(String clock) {
        try {
            Integer setTime = Integer.parseInt(clock);
            this.time = setTime;
        }
        catch (NumberFormatException e){
            return;
        }
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getTime() {
        return this.time;
    }

}
