package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.DocumentException;

/**
 * Interface used for creating an XML file for an object, and loading an object
 * from an XML file.
 * 
 * @author Liam Green - greenliam
 */
public interface XMLFactory<T> {
    /**
     * Create an object of type T from an XML file.
     * 
     * @param xmlFile the XML file to load from
     * @return the object created from the XML file
     * @throws DocumentException if the XML file is invalid
     */
    public T createFromXML(InputStream xmlStream) throws DocumentException;

    /**
     * Save an object of type T to an XML file.
     * 
     * @param filePath     the path to save the XML file to
     * @param objectToSave the object to save
     * @return the XML file that was saved to
     * @throws IOException if the file could not be saved
     */
    public File createXML(String filePath, T objectToSave) throws IOException;
}
