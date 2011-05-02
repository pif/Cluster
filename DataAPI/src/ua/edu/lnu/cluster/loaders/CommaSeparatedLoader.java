/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.loaders;

import ua.edu.lnu.cluster.loaders.api.Loader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.DataModel;

/**
 *
 * @author pif
 */
@ServiceProvider(service=Loader.class)
public class CommaSeparatedLoader implements Loader {

    private static final String regex = ",";
    
    @Override
    public String[] getObservation(String data) {
        return data.split(regex);
    }

    @Override
    public DataModel getData(BufferedReader reader, boolean headersFirst) {
        try {
            List<String[]> rows = new ArrayList<String[]>();
            List<String> headers = new ArrayList<String>();
            if (headersFirst) {
                String line = reader.readLine();
                if (line != null) {
                    headers.addAll(Arrays.asList(getObservation(line)));
                }
            }
            
            String line = reader.readLine();
            while (line != null) {
                if (line.length() > 0) {
                    rows.addAll(Collections.singletonList(getObservation(line)));
                }

                line = reader.readLine();
            }
            
            return new DataModel(rows, headers);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CommaSeparatedLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CommaSeparatedLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new DataModel();
    }

    @Override
    public String getName() {
        return "Comma-separated Values Loader";
    }
}