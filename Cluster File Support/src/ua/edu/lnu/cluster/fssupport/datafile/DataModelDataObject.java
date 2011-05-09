/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.fssupport.datafile;

import java.io.IOException;
import java.io.ObjectInputStream;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.loaders.OpenSupport;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.Exceptions;
import org.openide.windows.CloneableTopComponent;
import ua.edu.lnu.cluster.DataModel;
import ua.edu.lnu.cluster.ui.api.TCManager;

public class DataModelDataObject extends MultiDataObject {

    private DataModel model;
    private FileObject realFile;
    private CookieSet cookies;
    private OpenDataModelAction openAction;

    public DataModelDataObject(FileObject fileObject, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(fileObject, loader);
        realFile = fileObject;
        model = readFile(fileObject);
        
        cookies = getCookieSet();
        cookies.assign(DataModel.class, model);
        openAction = new OpenDataModelAction(getPrimaryEntry());

        cookies.add((Node.Cookie) openAction);
        cookies.add(this);
    }

// creates node for projects window
    @Override
    protected Node createNodeDelegate() {
        // can use DataNode here as well
        DataNode node = new DataNode(this, Children.LEAF, getLookup());
       // DataNode node = new DataNode(this, Children.LEAF, cookies.getLookup());
        //  node.setShortDescription("Name is " + getLookup().lookup(NeuralNetwork.class).toString());
        node.setDisplayName(realFile.getName());

        return node;
    }

    public DataModel getDataModel() {
        return model;
    }

    @Override
    public Lookup getLookup() {
        return cookies.getLookup();
    }

    private DataModel readFile(FileObject fileObject) {
        ObjectInputStream stream = null;
        try {
            stream = new ObjectInputStream(fileObject.getInputStream());
            try {
                DataModel loaded = (DataModel) stream.readObject();
                stream.close();

                return loaded;
            } catch (ClassNotFoundException ex) {
                Exceptions.printStackTrace(ex);
                stream.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    private static class OpenDataModelAction  extends OpenSupport implements OpenCookie{

        private DataModelDataObject dataObject;
        
        public OpenDataModelAction(DataModelDataObject.Entry primaryEntry) {
            super(primaryEntry);
            dataObject = (DataModelDataObject) primaryEntry.getDataObject();
        }
        
            @Override
    public void open() {
        // check if it is allready loaded before loading
        // nnDataObject.loadObject();
        DataModel nnet = dataObject.getDataModel();

        TCManager.getInstance().openDataModelWindow(nnet);
    }

        @Override
        protected CloneableTopComponent createCloneableTopComponent() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}
