package com.github.imrealnow;

import org.dom4j.Element;

/**
 * Interface used for creating an XML element for an object, and loading an
 * object from an XML element.
 * 
 * @author Liam Green - greenliam
 */
public interface ElementFactory<T> {
    /**
     * Create an XML element for an object of type T.
     * 
     * @param objectToConvert the object to convert to an XML element
     * @return the XML element created from the object
     */
    public Element createElement(T objectToConvert);

    /**
     * Create an object of type T from an XML element.
     * 
     * @param element the XML element to load from
     * @return the object created from the XML element
     */
    public T createFromElement(Element element);
}
