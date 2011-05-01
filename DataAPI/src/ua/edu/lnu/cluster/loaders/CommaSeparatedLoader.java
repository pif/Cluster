/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pif
 */
public class CommaSeparatedLoader implements Loader {

    private static final String regex = ",";
    
    @Override
    public List<String[]> getData(String data) {
        String[] vals = data.split(regex);
        return Arrays.asList(vals);
    }

    @Override
    public List<String[]> getData(BufferedReader reader) {
        try {
            List<String[]> rows = new ArrayList<String[]>();
            String line = reader.readLine();
            while (line != null) {
                if (line.length() > 0) {
                    rows.addAll(getData(line));
                }

                line = reader.readLine();
            }
            
            return rows;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CharSeparatedLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CharSeparatedLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ArrayList<String[]>();
    }
 
}
