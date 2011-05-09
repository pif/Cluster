/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.fssupport.datafile;

import java.io.IOException;
import javax.swing.Action;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.util.Lookup;
import org.openide.text.DataEditorSupport;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

public class DataSetDataObject extends MultiDataObject {

    public DataSetDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        CookieSet cookies = getCookieSet();
        cookies.add((Node.Cookie) DataEditorSupport.create(this, getPrimaryEntry(), cookies));

        
        cookies.assign(NeuralNetwork.class, neuralNetwork); // put it in lookup
        openAction = new NeuralNetOpenSupport(getPrimaryEntry());

        cookies.add((Node.Cookie) openAction);
        cookies.add(this);
    }

    @Override
    protected Node createNodeDelegate() {
        return new DataSetNode()DataNode(this, Children.LEAF, getLookup());
    }

    @Override
    public Lookup getLookup() {
        return getCookieSet().getLookup();
    }
    
    private static final class DataSetNode extends FilterNode {

         public DataSetNode(Node node) throws DataObjectNotFoundException {
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
        public Action getPreferredAction() {
            
        }
        
    }
}
