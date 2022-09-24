package com.github.imrealnow;

import java.io.File;
import java.io.IOException;

import org.dom4j.DocumentException;

/**
 * Interface used for creating an XML file for an object, and loading an object
 * from an XML file.
 * 
 * @author Liam Green - greenliam
 */
public interface XMLFactory<T> {
    /**
     * Save an object of type T to an XML file.
     * 
     * @param filePath     the path to save the XML file to
     * @param objectToSave the object to save
     * @return the XML file that was saved to
     * @throws IOException if the file could not be saved
     */
    public File createXML(String filePath, T objectToSave) throws IOException;

    /**
     * Load an object of type T from an XML file.
     * 
     * @param file the XML file to load from
     * @return the object created from the XML file
     * @throws DocumentException if the file could not be loaded
     */
    public T createFromXML(File file) throws DocumentException;
}
