/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.fssupport.actions;

import org.openide.cookies.OpenCookie;
import org.openide.loaders.OpenSupport;
import org.openide.windows.CloneableTopComponent;
import ua.edu.lnu.cluster.DataModel;
import ua.edu.lnu.cluster.fssupport.DataModelDataObject;
import ua.edu.lnu.cluster.utils.TCManager;

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
        DataModel model = dataObject.getDataModel();

        TCManager.getInstance().openDataModelWindow(model);
    }

    @Override
    protected CloneableTopComponent createCloneableTopComponent() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
