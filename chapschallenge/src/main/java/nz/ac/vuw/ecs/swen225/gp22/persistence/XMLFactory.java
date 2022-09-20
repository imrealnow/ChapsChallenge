package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.io.IOException;

import org.dom4j.DocumentException;

/**
 * Interface used for creating an XML file for an object, and loading an object
 * from an XML file.
 * 
 * @author Liam Green - greenliam
 */
public interface XMLFactory<T> {
    public T createFromXML(String xml) throws DocumentException;

    public String createXML(T object) throws IOException;
}
