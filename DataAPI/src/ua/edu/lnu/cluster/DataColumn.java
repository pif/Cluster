/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

/**
 * provides basic data column information
 * @author pif
 */
public abstract class DataColumn {
    
    private DataInterpreter interpreter = null;
    
    private String name = "";
    
    public String getOriginalValue(int observation){}

    public double getNormalizedValue(int observation){}

    public void addData(String value){}

    /**
     *
     * @param observation
     * @return previous value
     */
    public void removeData(int observation){}

    /**
     *
     * @param observation
     * @param value
     * @return previous value
     */
    public void setData(int observation, String value){}
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
}
