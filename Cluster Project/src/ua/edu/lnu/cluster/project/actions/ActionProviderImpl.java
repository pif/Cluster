/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.project.actions;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ActionProvider;
import org.netbeans.spi.project.ui.support.DefaultProjectOperations;
import org.openide.util.Lookup;
import ua.edu.lnu.cluster.project.DataModelProject;

/**
 *
 * @author pif
 */
public class ActionProviderImpl implements ActionProvider {

    private static String[] supported = new String[]{
        ActionProvider.COMMAND_DELETE,
        ActionProvider.COMMAND_COPY,};

    private final Project project;

    public ActionProviderImpl(Project project) {
        this.project = project;
    }
    
    @Override
    public String[] getSupportedActions() {
        return supported;
    }

    @Override
    public void invokeAction(String string, Lookup lookup) throws IllegalArgumentException {
        if (ActionProvider.COMMAND_DELETE.equalsIgnoreCase(string)) {
            DefaultProjectOperations.performDefaultDeleteOperation(project);
        }
        if (ActionProvider.COMMAND_COPY.equalsIgnoreCase(string)) {
            DefaultProjectOperations.performDefaultCopyOperation(project);
        }
    }

    @Override
    public boolean isActionEnabled(String command, Lookup lookup) throws IllegalArgumentException {
        if ((ActionProvider.COMMAND_DELETE.equals(command))) {
            return true;
        } else if ((ActionProvider.COMMAND_COPY.equals(command))) {
            return true;
        } else {
            throw new IllegalArgumentException(command);
        }
    }
}
