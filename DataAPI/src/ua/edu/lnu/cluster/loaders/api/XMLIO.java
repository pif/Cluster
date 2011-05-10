/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.loaders.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;
import ua.edu.lnu.cluster.DataColumn;
import ua.edu.lnu.cluster.DataModel;
import ua.edu.lnu.cluster.Observation;
import ua.edu.lnu.cluster.interpreters.api.DataInterpreter;

/**
 *  Look at Cluster File Support module for a file DataModelTemplate.cluster
 * That file show how should xml file look like.
 * @author pif
 */
public class XMLIO {

    public static final String ROOT = "cluster";
    public static final String NAME = "name";
    public static final String COLUMNS = "columns";
    public static final String COLUMN_PROPS = "column";
    public static final String COLUMN_NAME = "colname";
    public static final String COLUMN_USED = "used";
    public static final String COLUMN_INTERPRETER = "type";
    public static final String DATA = "data";
    public static final String ITEM = "item";
    public static final String VALUE = "el";

    public DataModel getDataModel(InputStream input) {
        DataModelParser parser = new DataModelParser();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(input, parser);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLIO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return parser.getModel();
        }
    }

    public void write(DataModel model, OutputStream output) {
        try {
            StreamResult result = new StreamResult(output);
            SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler hd = tf.newTransformerHandler();
            Transformer serializer = hd.getTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            hd.setResult(result);
            hd.startDocument();
            AttributesImpl atts = new AttributesImpl();

            hd.startElement("", "", ROOT, atts);

            atts.clear();
            hd.startElement("", "", DATA, atts);
            int cnt = model.getObservationCount();
            int fcnt = model.getFeaturesCount();
            for (int i = 0; i < cnt; i++) {
                Observation observation = model.getObservation(i);
                hd.startElement("", "", ITEM, atts);
                for (int j = 0; j < fcnt; j++) {
                    hd.startElement("", "", VALUE, atts);
                    String value = observation.getField(j).toString();
                    hd.characters(value.toCharArray(), 0, value.length());
                    hd.endElement("", "", VALUE);
                }
                hd.endElement("", "", ITEM);
            }
            hd.endElement("", "", DATA);

            hd.startElement("", "", COLUMNS, atts);
            for (DataColumn dataColumn : model.getDataColumns()) {
                atts.clear();
                atts.addAttribute("", "", COLUMN_NAME, "CDATA", dataColumn.getName());
                atts.addAttribute("", "", COLUMN_USED, "CDATA", dataColumn.isUsedInCalculations() + "");
                atts.addAttribute("", "", COLUMN_INTERPRETER, "CDATA", dataColumn.getInterpreter().getClass().getName());
                hd.startElement("", "", COLUMN_PROPS, atts);
                hd.endElement("", "", COLUMN_PROPS);
            }
            hd.endElement("", "", COLUMNS);

            hd.endElement("", "", ROOT);
            hd.endDocument();
        } catch (SAXException ex) {
            Logger.getLogger(XMLIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class DataModelParser extends DefaultHandler {

        private DataModel model;
        private boolean columns = false;
        private boolean column = false;
        private boolean data = false;
        private boolean item = false;
        private boolean el = false;
        private List<String[]> values = new ArrayList<String[]>();
        private List<String> value = new ArrayList<String>();
        private int columnIndex = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes atrbts) throws SAXException {
            if (VALUE.equalsIgnoreCase(qName)) {
                el = true;
            } else if (ITEM.equalsIgnoreCase(qName)) {
                value = new ArrayList<String>();
                item = true;
            } else if (COLUMNS.equalsIgnoreCase(qName)) {
                columns = true;
            } else if (COLUMN_PROPS.equalsIgnoreCase(qName)) {
                try {
                    model.getDataColumn(columnIndex).setName(atrbts.getValue(COLUMN_NAME));
                    model.getDataColumn(columnIndex).setUsedInCalculations(Boolean.valueOf(atrbts.getValue(COLUMN_USED)));
                    DataInterpreter interpreter = (DataInterpreter) Class.forName(atrbts.getValue(COLUMN_INTERPRETER)).newInstance();
                    // TODO think of map in Lookup, in which all the interpreters register themselves with specific word
                    model.getDataColumn(columnIndex).setInterpreter(interpreter);
                    column = true;
                } catch (InstantiationException ex) {
                    Logger.getLogger(XMLIO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(XMLIO.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(XMLIO.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (DATA.equalsIgnoreCase(qName)) {
                data = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (VALUE.equalsIgnoreCase(qName)) {
                el = false;
            } else if (ITEM.equalsIgnoreCase(qName)) {
                String[] vals = new String[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    vals[i] = value.get(i);
                }
                values.add(vals);
                item = false;
            } else if (COLUMNS.equalsIgnoreCase(qName)) {
                columns = false;
            } else if (COLUMN_PROPS.equalsIgnoreCase(qName)) {
                columnIndex++;
                column = false;
            } else if (DATA.equalsIgnoreCase(qName)) {
                System.out.println("Inside " + DATA);
                model = new DataModel(values);
                data = false;
            }
        }

        @Override
        public void characters(char[] chars, int start, int length) throws SAXException {
            if (el) {
                value.add(new String(chars, start, length));
            }
        }

        public DataModel getModel() {
            if (model == null) {
                model = new DataModel();
            }

            return model;
        }
    }
}
