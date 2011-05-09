/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.project.actions;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.CopyOperationImplementation;
import org.openide.filesystems.FileObject;

/**
 *
 * @author pif
 */
public class CopyOperation implements CopyOperationImplementation {

    private final Project project;
    private final FileObject projectDir;

    public CopyOperation(Project project) {
        this.project = project;
        this.projectDir = project.getProjectDirectory();
    }

    @Override
    public List<FileObject> getMetadataFiles() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<FileObject> getDataFiles() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void notifyCopying() throws IOException {
    }

    @Override
    public void notifyCopied(Project arg0, File arg1, String arg2) throws IOException {
    }
}