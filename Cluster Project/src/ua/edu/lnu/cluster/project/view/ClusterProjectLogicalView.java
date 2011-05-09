/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.project.view;

import javax.swing.Action;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import ua.edu.lnu.cluster.project.DataModelProject;

/**
 *
 * @author pif
 */
public class ClusterProjectLogicalView implements LogicalViewProvider {

    private final DataModelProject project;

    public ClusterProjectLogicalView(DataModelProject project) {
        this.project = project;
    }

    @Override
    public Node createLogicalView() {
        //Get the Text directory, creating if deleted
        FileObject cluster = project.getClusterFolder(true);

        //Get the DataObject that represents it
        DataFolder clusterFolderDataObject =
                DataFolder.findFolder(cluster);

        //Get its default node-we'll wrap our node around it to change the
        //display name, icon, etc
        Node realNodeForFolder = clusterFolderDataObject.getNodeDelegate();

        //This FilterNode will be our project node
        return realNodeForFolder;//new TextNode(realNodeForFolder, project);
    }

    @Override
    public Node findPath(Node node, Object o) {
        return null;
    }

    private static final class ClusterFolderNode extends FilterNode {

        final DataModelProject project;

        public ClusterFolderNode(Node node, DataModelProject project) throws DataObjectNotFoundException {
            super(node, new FilterNode.Children(node),
                    //The projects system wants the project in the Node's lookup.
                    //NewAction and friends want the original Node's lookup.
                    //Make a merge of both
                    new ProxyLookup(new Lookup[]{Lookups.singleton(project),
                        node.getLookup()
                    }));
            this.project = project;
        }

        @Override
        public Action[] getActions(boolean arg0) {
            Action[] nodeActions = new Action[7];
            nodeActions[0] = CommonProjectActions.newFileAction();
            nodeActions[1] = CommonProjectActions.copyProjectAction();
            nodeActions[2] = CommonProjectActions.deleteProjectAction();
            nodeActions[5] = CommonProjectActions.setAsMainProjectAction();
            nodeActions[6] = CommonProjectActions.closeProjectAction();
            return nodeActions;
        }
    }
}
