/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.columns;

/**
 * provides basic data column information
 * @author pif
 */
public abstract class DataColumn {

    public abstract String getStringValue(int observation);

    public abstract double getNormalizedValue(int observation);

    public abstract void addData(String value);

    /**
     *
     * @param observation
     * @return previous value
     */
    public abstract void removeData(int observation);

    /**
     *
     * @param observation
     * @param value
     * @return previous value
     */
    public abstract void setData(int observation, String value);
}
