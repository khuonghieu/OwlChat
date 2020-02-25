package ch.ethz.inf.vs.a3.solution.clock;

import java.util.HashMap;
import java.lang.StringBuilder;
import ch.ethz.inf.vs.a3.clock.Clock;

public class VectorClock implements Clock {

    private HashMap<Integer, Integer> vector = new HashMap<>();

    @Override
    public void update(Clock other) {
        // TODO Auto-generated method stub
        HashMap<Integer, Integer> updateVector = ((VectorClock) other).vector;
        for (Integer key : updateVector.keySet()) {
            Integer updateTime = updateVector.get(key);
            if (this.vector.containsKey(key)) {
                if (updateTime.compareTo(this.vector.get(key)) > 0) {
                    this.vector.replace(key, updateTime);
                }
            } else {
                this.vector.put(key, updateTime);
            }
        }

    }

    @Override
    public void setClock(Clock other) {
        HashMap<Integer, Integer> updateVector = ((VectorClock) other).vector;
        for (Integer pid : this.vector.keySet()) {
            Integer updateTime = updateVector.get(pid);
            this.vector.replace(pid, updateTime);
        }
    }

    @Override
    public void tick(Integer pid) {
        // TODO Auto-generated method stub
        Integer tickTime = this.vector.get(pid);
        this.vector.replace(pid, tickTime + 1);

    }

    @Override
    public boolean happenedBefore(Clock other) {
        // TODO Auto-generated method stub
        HashMap<Integer, Integer> otherVector = ((VectorClock) other).vector;
        boolean less = true;
        for (Integer key : this.vector.keySet()) {
            if (otherVector.containsKey(key)) {
                if (this.vector.get(key).compareTo(otherVector.get(key)) > 0) {
                    return false;
                }
            }
        }
        return less;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (this.vector.size() != 0) {
            for (Integer key : this.vector.keySet()) {
                sb.append("\"");
                sb.append(key.toString());
                sb.append("\"");
                sb.append(":");
                sb.append(this.vector.get(key).toString());
                sb.append(",");
            }
            sb.replace(sb.length() - 1, sb.length(), "");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void setClockFromString(String clock) {
        if (clock.length() == 2) {
            this.vector = new HashMap<Integer, Integer>();
        } else {
            String noCurly = clock.substring(1, clock.length() - 1);
            String[] pairs = noCurly.split(",");
            HashMap<Integer, Integer> tempMap = new HashMap<>();
            for (String pair : pairs) {
                String[] keyValue = pair.split(":");
                try {
                    Integer key = Integer.valueOf(keyValue[0].substring(1, keyValue[0].length() - 1));
                    Integer value = Integer.valueOf(keyValue[1]);
                    tempMap.put(key, value);
                } catch (NumberFormatException e) {
                    return;
                }
            }
            this.vector = new HashMap<Integer, Integer>();
            this.vector.putAll(tempMap);
        }
    }

    public int getTime(Integer pid) {
        return this.vector.get(pid);
    }

    public void addProcess(Integer pid, int time) {
        this.vector.put(pid, Integer.valueOf(time));
    }
}
