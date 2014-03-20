package warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * User: Tomas
 * Date: 11.03.14
 * Time: 23:29
 */
public class Warehouse<T extends Comparable<T>> {
    private final NavigableMap<T, Integer> byReceived = createNavigableMap();
    private final NavigableMap<T, Integer> byDispatched = createNavigableMap();

    protected NavigableMap<T, Integer> createNavigableMap() {
        return new TreeMap<T, Integer>();
    }

    public void addCar(T received, T dispatched) {
        addToMap(byReceived, received);
        addToMap(byDispatched, dispatched);
    }

    private void addToMap(Map<T, Integer> map, T key) {
        Integer value = map.get(key);
        if (value == null) {
            map.put(key, 1);
        }
        else {
            map.put(key, value + 1);
        }
    }

    public Result findMaxCars() {
        if (byReceived.isEmpty()) {
            return new Result();
        }

        T previous = byReceived.firstKey();
        int number = byReceived.get(previous);
        Result result = new Result(number, new Interval(previous, byDispatched.firstKey()));

        for (Map.Entry<T, Integer> entry : byReceived.tailMap(previous, false).entrySet()) {
            number += entry.getValue();
            T current = entry.getKey();

            for (Integer ended : byDispatched.subMap(previous, false, current, true).values()) {
                number -= ended;
            }

            if (number == result.number) {
                final int lastIndex = result.getIntervals().size() - 1;
                Interval last = result.getIntervals().get(lastIndex);
                if (last.end.compareTo(current) == 0) {
                    //merge intervals
                    result.getIntervals().remove(lastIndex);
                    result.getIntervals().add(new Interval(last.begin, byDispatched.tailMap(current, false).firstKey()));
                }
                else {
                    result.getIntervals().add(new Interval(current, byDispatched.tailMap(current, false).firstKey()));
                }
            }
            else if (number > result.number) {
                result.setNumber(number);
                result.getIntervals().clear();
                result.getIntervals().add(new Interval(current, byDispatched.tailMap(current, false).firstKey()));
            }

            previous = current;
        }

        return result;
    }

    public class Interval {
        private final T begin;
        private final T end;

        public Interval(T begin, T end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Interval interval = (Interval) o;

            if (!begin.equals(interval.begin)) return false;
            if (!end.equals(interval.end)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = begin.hashCode();
            result = 31 * result + end.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "begin=" + begin +
                    ", end=" + end +
                    '}';
        }
    }

    public class Result {
        private int number;
        private List<Interval> intervals = new ArrayList<Interval>();

        public Result() {
        }

        public Result(int number, Interval interval) {
            this.number = number;
            this.intervals.add(interval);
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<Interval> getIntervals() {
            return intervals;
        }

        public void setIntervals(List<Interval> intervals) {
            this.intervals = intervals;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Result result = (Result) o;

            if (number != result.number) return false;
            if (!intervals.equals(result.intervals)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = number;
            result = 31 * result + intervals.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "number=" + number +
                    ", intervals=" + intervals +
                    '}';
        }
    }
}
