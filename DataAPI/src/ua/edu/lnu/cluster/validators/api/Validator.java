/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.validators.api;

/**
 *
 * @author pif
 */
public interface Validator {
    
    public static int LEVEL_ERROR = 20;
    public static int LEVEL_WARNING = 10;
    public static int LEVEL_GOOD = 0;
    
    int isEntryValid(Object entry);
}
