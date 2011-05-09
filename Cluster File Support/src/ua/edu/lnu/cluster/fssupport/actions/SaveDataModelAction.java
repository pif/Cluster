/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.fssupport.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import ua.edu.lnu.cluster.DataModel;
import ua.edu.lnu.cluster.fssupport.DataModelDataObject;

/**
 *
 * @author pif
 */
public class SaveDataModelAction implements SaveCookie {

    private DataModelDataObject dataObject;

    public SaveDataModelAction(DataModelDataObject dataObject) {
        this.dataObject = dataObject;
    }

    @Override
    public void save() throws IOException {
        if (dataObject != null) {
            File saveTo = FileUtil.toFile(dataObject.getPrimaryFile());
            save(saveTo);
        } else {
            saveAs();
        }
    }

    public void saveAs() throws IOException {
        File f = new FileChooserBuilder(SaveDataModelAction.class).setTitle("Save file").showSaveDialog();
        if (f != null) {
            if (!f.getAbsolutePath().endsWith(".cluster")) {
                f = new File(f.getAbsolutePath() + ".cluster");
            }
            try {
                if (!f.exists()) {
                    if (!f.createNewFile()) {
                        // TODO fail message!
                        return;
                    }
                } else {
                    // TODO overwrite?
                }
                save(f.getAbsoluteFile());
            } catch (IOException ioe) {
                Exceptions.printStackTrace(ioe);
            }
        }
    }

    private void save(File f) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(f));
        try {
            stream.writeObject(dataObject.getLookup().lookup(DataModel.class));
        } finally {
            stream.close();
        }
    }
}
