/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import java.util.List;

/**
 *
 * @author pif
 */
public abstract class DataInterpreter {

    public abstract double convertValue(String value);

    public abstract void processData(List<String> data);

    public abstract String getName();

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
}
