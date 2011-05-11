/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.dataobj;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.CookieSet;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.Exceptions;
import ua.edu.lnu.cluster.DataColumn;
import ua.edu.lnu.cluster.DataModel;
import ua.edu.lnu.cluster.dataobj.abilities.OpenDataModelAction;
import ua.edu.lnu.cluster.loaders.api.XMLIO;

public class DataModelDataObject extends MultiDataObject implements PropertyChangeListener {

    private DataModel model;
    private FileObject realFile;
    private CookieSet cookies;
    private Saver saver = new Saver();

    public DataModelDataObject(FileObject fileObject, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(fileObject, loader);
        realFile = fileObject;
        model = readFile(fileObject);

        cookies = getCookieSet();
        if (model != null) {
            cookies.assign(DataModel.class, model);
        }

        cookies.add((Node.Cookie) new OpenDataModelAction(getPrimaryEntry()));
        cookies.add(this);
        
        model.addPropertyChangeListener(this);
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
        XMLIO reader = new XMLIO();
        try {
            return reader.getDataModel(fileObject.getInputStream());
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        String propName = pce.getPropertyName();
        if (DataModel.PROP_NAME.equals(propName) 
                || DataModel.PROP_DATA.equals(propName)
                || DataColumn.PROP_NAME.equals(propName)
                || DataColumn.PROP_TYPE.equals(propName)
                || DataColumn.PROP_USED.equals(propName)
                || DataColumn.PROP_VALUES.equals(propName)) {
            enableSaveAction(true);
        }
    }

    private class Saver implements SaveCookie {

        @Override
        public void save() throws IOException {
            FileObject saveTo = DataModelDataObject.this.getPrimaryFile();
            if (saveTo != null) {
                XMLIO writer = new XMLIO();
                OutputStream out = saveTo.getOutputStream();
                writer.write(model, out);
                out.close();
                enableSaveAction(false);
            }
        }
    }

    private void enableSaveAction(boolean b) {
        if (b) {
            cookies.add(saver);
        } else {
            cookies.remove(saver);
        }
    }
}
