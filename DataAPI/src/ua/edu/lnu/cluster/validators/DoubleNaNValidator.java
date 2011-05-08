/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.validators;

import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.validators.api.Validator;

/**
 *
 * @author pif
 */
@ServiceProvider(service=Validator.class)
public class DoubleNaNValidator implements Validator {

    @Override
    public int isEntryValid(Object entry) {
        if (entry instanceof Double) {
            double d = (Double)entry;
            if (d == Double.NaN){
                return LEVEL_ERROR;
            } else if (d == Double.NEGATIVE_INFINITY || d==Double.POSITIVE_INFINITY) {
                return LEVEL_WARNING;
            } 
        }
        return LEVEL_GOOD;
    }
    
}
