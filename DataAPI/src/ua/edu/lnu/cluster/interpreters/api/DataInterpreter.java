/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters.api;

import java.util.List;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author pif
 */
public abstract class DataInterpreter {

    public abstract double convertValue(String value);

    /**
     * we wish that table columns use default renderer/cell editors for 
     * specific columns. Column class is taken from getColumnClass() method.
     * This means that the underlying interpreter always knows which type it's
     * dealing with. So, one can easily cast (Object)value -> (WhatEver)value
     * @param value instance of getColumnClass() class.
     * @return string representation, will be saved 
     * in the DataModel as raw data.
     */
    public abstract String reverseTranslate(Object value);

    public abstract void preprocessData(List<String> data);

    public abstract String getName();
    
    public Class<?> getColumnClass() {
        return Object.class;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataInterpreter other = (DataInterpreter) obj;
        return getName().equalsIgnoreCase(other.getName());
    }

    @Override
    public int hashCode() {
        return 37*getName().hashCode();
    }
    
    /**
     * 
     * @return 1) null if this class doesn't provide any custom renderer. 
     * e.g. default renderer is enough
     * 2) TableCellEditor which will be registered for this specific DataType.
     
    public TableCellEditor getCustomCellEditor() {
        return null;
    }*/
}
