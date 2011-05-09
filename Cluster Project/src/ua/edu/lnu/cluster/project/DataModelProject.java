/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.project;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author pif
 */
public class DataModelProject implements Project{

    private final FileObject projectDir;
    private final ProjectState state;
    private Lookup lookup;
    
    public DataModelProject(FileObject fo, ProjectState ps) {
        this.projectDir = fo;
        this.state = ps;
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    @Override
    public Lookup getLookup() {
        if (lookup == null) {
            lookup = Lookups.fixed(new Object[] {
                state
                // TODO add actions.
            });
        }
        return lookup;
    }

    FileObject getClusterFolder(boolean create) {
        FileObject res = projectDir.getFileObject(DataModelPrjFactory.PROJECT_DIR);
        if (res == null && create) {
            try {
                res = projectDir.createFolder(DataModelPrjFactory.PROJECT_DIR);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        
        return res;
    }
    
}
