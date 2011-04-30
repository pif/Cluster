/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

/**
 *
 * @author pif
 */
public class Observation {
    private Object[] fields = new Object[0];

    public Observation(Object[] fields) {
        this.fields = fields;
    }

    public Object getField(int index) {
        return fields[index];
    }
}
