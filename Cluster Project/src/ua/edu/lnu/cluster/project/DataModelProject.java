/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.project;

import ua.edu.lnu.cluster.project.view.ClusterProjectLogicalView;
import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import ua.edu.lnu.cluster.project.actions.ActionProviderImpl;
import ua.edu.lnu.cluster.project.actions.CopyOperation;
import ua.edu.lnu.cluster.project.actions.DeleteOperation;
import ua.edu.lnu.cluster.project.actions.Info;

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

    public FileObject getClusterFolder(boolean create) {
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

    //The project type's capabilities are registered in the project's lookup:
    @Override
    public Lookup getLookup() {
        if (lookup == null) {
            lookup = Lookups.fixed(new Object[]{
                        state, //allow outside code to mark the project as needing saving
                        new ActionProviderImpl(this), //Provides standard actions like Build and Clean
                        new DeleteOperation(this),
                        new CopyOperation(this),
                        new Info(this)//, //Project information implementation
                        //new ClusterProjectLogicalView(this), //Logical view of project implementation
                    });
        }
        return lookup;
    }

    
}
