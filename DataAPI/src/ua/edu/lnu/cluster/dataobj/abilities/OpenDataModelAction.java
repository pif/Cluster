/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.dataobj.abilities;

import org.openide.cookies.OpenCookie;
import org.openide.loaders.OpenSupport;
import org.openide.util.Lookup;
import org.openide.windows.CloneableTopComponent;
import ua.edu.lnu.cluster.dataobj.DataModelDataObject;
import ua.edu.lnu.cluster.dataobj.abilities.api.DataModelTcOpener;

/**
 *
 * @author pif
 */
public class OpenDataModelAction extends OpenSupport implements OpenCookie {

    private DataModelDataObject dataObject;

    public OpenDataModelAction(DataModelDataObject.Entry primaryEntry) {
        super(primaryEntry);
        dataObject = (DataModelDataObject) primaryEntry.getDataObject();
    }

    @Override
    public void open() {
        Lookup.getDefault().lookup(DataModelTcOpener.class).openDataModelWindow(dataObject);
    }

    @Override
    protected CloneableTopComponent createCloneableTopComponent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
