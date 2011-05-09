/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.explorer;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import ua.edu.lnu.cluster.DataColumn;
import ua.edu.lnu.cluster.DataModel;

/**
 * used by DataModelNode to create it's siblings corresponding to DataColumns
 * @author pif
 */
class DataColumnsChildren extends ChildFactory<DataColumn> {

    private DataModel data = null;

    public DataColumnsChildren(DataModel data) {
        this.data = data;
    }

    @Override
    protected boolean createKeys(List<DataColumn> toPopulate) {
        toPopulate.addAll(data.getDataColumns());
        return true;
    }

    @Override
    protected Node createNodeForKey(DataColumn key) {
        Node result = new DataColumnNode(key);
        return result;
    }
}
