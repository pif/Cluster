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
public class EmptyDataValidator implements Validator{

    @Override
    public boolean isEntryValid(String entry) {
        boolean valid = entry!=null && !entry.trim().isEmpty();
        return valid;
    }
    
}
