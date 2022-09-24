package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * Class used for loading a recording from an XML file.
 *
 * @author Julia Huijser - huijsejuli
 */
public class RecorderMain {
    public static void main(String[] args) {
        // saving and loading files can only be done from the persistence module
        try {
            File file = new File("enter file here"); // TODO: Enter file here.
            Document document = new SAXReader().read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
