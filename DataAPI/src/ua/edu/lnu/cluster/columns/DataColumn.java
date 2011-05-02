/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.columns;

/**
 * provides basic data column information
 * @author pif
 */
public interface DataColumn {

    String getStringValue(int observation);

    double getNormalizedValue(int observation);

    void addData(String value);

    /**
     *
     * @param observation
     * @return previous value
     */
    void removeData(int observation);

    /**
     *
     * @param observation
     * @param value
     * @return previous value
     */
    void setData(int observation, String value);
}
