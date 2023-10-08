package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        if (ts == null) {
            return ;
        } else {
            for (Integer i: ts.keySet()) {
                if (i >= startYear && i <= endYear) {
                    put(i, ts.get(i));
                }
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> yearsList = new ArrayList<>(keySet());
        return yearsList;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> dataList = new ArrayList<>();
        List<Integer> yearsList = new ArrayList<>(keySet());
        for (Integer integer : yearsList) {
            dataList.add(get(integer));
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries tsCopy = new TimeSeries();
        if (isEmpty() && ts.isEmpty()) {
            return tsCopy;
        }
        for(Integer integer : keySet()) {
            if (!ts.containsKey(integer)) {
                tsCopy.put(integer, get(integer));
            } else {
                tsCopy.put(integer, get(integer) + ts.get(integer));
            }
        }
        for (Integer integer: ts.keySet()) {
            if (!containsKey(integer)) {
                tsCopy.put(integer, ts.get(integer));
            }
        }
        return tsCopy;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries tsCopy = new TimeSeries();
        if (isEmpty() && ts.isEmpty()) {
            return tsCopy;
        }
        for (Integer integer: keySet()) {
            if (!ts.containsKey(integer)) {
                throw new IllegalArgumentException();
            } else {
                tsCopy.put(integer, get(integer) / ts.get(integer));
            }
        }
        return tsCopy;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
