package nz.ac.vuw.ecs.swen225.gp22.recorder;
import java.io.*;
import java.util.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * Class used for loading a recording from an XML file.
 *
 * @author Julia Huijser - huijsejuli
 */
public class RecorderMain {
    public static void main(String[] args) {
        try {
            File file = new File("enter file here"); // TODO: Enter file here.
            Document document = new SAXReader().read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
}
