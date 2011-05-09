/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.project;

import java.io.IOException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ProjectFactory;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author pif
 */
@ServiceProvider(service=ProjectFactory.class)
public class DataModelPrjFactory implements ProjectFactory {

    public static final String PROJECT_DIR = "cluster";

    /**
     * specifies, if the directory is a project, if and only if it contains
     * PROJECT_DIR
     * @param fo
     * @return 
     */
    @Override
    public boolean isProject(FileObject fo) {
        return fo.getFileObject(PROJECT_DIR) != null;
    }

    @Override
    public Project loadProject(FileObject fo, ProjectState ps) throws IOException {
        return isProject(fo) ? new DataModelProject(fo, ps) : null;
    }

    @Override
    public void saveProject(Project project) throws IOException, ClassCastException {
        FileObject prjDir = project.getProjectDirectory();
        if (prjDir.getFileObject(PROJECT_DIR) == null) {
            throw new IOException("Project directory "+prjDir.getPath() +
                    " deleted,"+
                    " unable to save the project.");
        }
        
        ((DataModelProject)project).getClusterFolder(true);
    }
}
