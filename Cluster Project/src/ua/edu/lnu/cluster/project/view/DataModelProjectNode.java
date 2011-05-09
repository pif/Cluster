package ua.edu.lnu.cluster.project.view;

import java.awt.Image;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import ua.edu.lnu.cluster.project.DataModelProject;

/**
 * This is the root node for every project
 * @author Ivana
 */
public class DataModelProjectNode extends FilterNode {

    final DataModelProject project;

    public DataModelProjectNode(Node node, DataModelProject project) throws DataObjectNotFoundException {

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

    @Override
    public Image getIcon(int type) {
        return ImageUtilities.loadImage(
                "ua/edu/lnu/cluster/project/actions/chart.png");
    }

    public void addSubNodes(Node[] n) {
        this.getChildren().add(n);
    }

    @Override
    public Image getOpenedIcon(int type) {
        return getIcon(type);
    }

    @Override
    public String getDisplayName() {
        return project.getProjectDirectory().getName();
    }
}