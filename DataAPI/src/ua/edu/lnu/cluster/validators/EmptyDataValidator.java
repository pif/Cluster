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
@ServiceProvider(service = Validator.class)
public class EmptyDataValidator implements Validator {

    @Override
    public int isEntryValid(Object entry) {
        if (entry instanceof String) {
            boolean valid = entry != null && !((String) entry).trim().isEmpty();
            return valid ? Validator.LEVEL_GOOD : Validator.LEVEL_ERROR;
        }
        return LEVEL_GOOD;
    }
}
