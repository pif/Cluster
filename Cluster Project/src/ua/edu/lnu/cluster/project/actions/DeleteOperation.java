/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.project.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.DeleteOperationImplementation;
import org.openide.filesystems.FileObject;

/**
 *
 * @author pif
 */
public class DeleteOperation implements DeleteOperationImplementation {

    private final Project project;

    public DeleteOperation(Project project) {
        this.project = project;
    }
    
    @Override
    public void notifyDeleting() throws IOException {
    }

    @Override
    public void notifyDeleted() throws IOException {
    }

    @Override
    public List<FileObject> getMetadataFiles() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<FileObject> getDataFiles() {
        return Collections.EMPTY_LIST;
    }
}
