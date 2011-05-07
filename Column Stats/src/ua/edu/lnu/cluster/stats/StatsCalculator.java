/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.stats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ua.edu.lnu.cluster.DataColumn;

/**
 *
 * @author pif
 */
public class StatsCalculator {

    private DataColumn column = null;
    private List<Double> values = new ArrayList<Double>();
    private Map<Double, Integer> dyskretnyy = new HashMap<Double, Integer>();

    public DataColumn getColumn() {
        return column;
    }

    public void setColumn(DataColumn column) {
        this.column = column;
        this.values.clear();
        this.values.addAll(column.getNormalizedValues());
        Collections.sort(values);
        
        dyskretnyy.clear();
        for (Double value : values) {
            if (dyskretnyy.containsKey(value)) {
                dyskretnyy.put(value, dyskretnyy.get(value) + 1);
            } else {
                dyskretnyy.put(value, 1);
            }
        }
    }

    public double getModa() {
        Set<Map.Entry<Double, Integer>> pairs = dyskretnyy.entrySet();
        Map.Entry<Double, Integer> moda = pairs.iterator().next();
        for (Map.Entry<Double, Integer> entry : pairs) {
            if (entry.getValue() > moda.getValue()) {
                moda = entry;
            }
        }

        return moda.getKey();
    }

    public double getMediana() {
        if (values.size() % 2 == 1) {
            return values.get(values.size() / 2);
        } else {
            return (values.get(values.size() / 2 - 1) + values.get(values.size() / 2)) / 2;
        }
    }

    public double getAverage() {
        double sum = 0;
        for (Double value : values) {
            sum += value;
        }

        return sum / column.getSize();
    }

    public double getRozmakh() {
        return getMax() - getMin();
    }

    public double getMin() {
        return values.get(0);
    }

    public double getMax() {
        return values.get(values.size() - 1);
    }

    public double getVariansa() {
        int n = values.size();
        double sum = 0;
        double xsv = getAverage();
        for (double item : values) {
            sum += Math.pow(item - xsv, 2);
        }
        return sum / (n - 1);
    }

    public double getStandart() {
        return Math.sqrt(getVariansa());
    }

    public double getCentralnyMoment(int poriadok) {
        double sum = 0;
        double xsv = getAverage();
        for (double item : values) {
            sum += Math.pow(item - xsv, poriadok);
        }
        return sum / values.size();
    }

    public double getAsymetria() {
        return getCentralnyMoment(3) / Math.pow(getCentralnyMoment(2), 1.5d);
    }

    public double getEksces() {
        return getCentralnyMoment(4) / Math.pow(getCentralnyMoment(2), 2) - 3;
    }
}
