package com.github.imrealnow;

import java.io.File;
import java.io.IOException;

import org.dom4j.DocumentException;

/**
 * Interface for turning objects into XML files and back again.
 */
public interface XMLFactory<T> {
    public T fromXML(File xmlFile) throws DocumentException;

    public File toXML(String path, String fileName) throws IOException;
}
