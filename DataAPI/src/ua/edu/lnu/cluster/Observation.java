/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import java.io.Serializable;

/**
 *
 * @author pif
 */
public class Observation implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Object[] fields = new Object[0];

    public Observation(Object[] fields) {
        this.fields = fields;
    }

    public Object getField(int index) {
        return fields[index];
    }
}
