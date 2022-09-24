package nz.ac.vuw.ecs.swen225.gp22.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Utility class for creating a file chooser for saving and loading XML files.
 * 
 * @author Liam Green - greenliam
 */
public class XMLSerializer {
    enum FileAction {
        SAVE, LOAD
    }

    /**
     * Writes a Dom4j document to an XML file.
     * 
     * @param document the document to write
     * @param path     the path to write the document to
     * @return the file that was written to
     * @throws IOException if the file could not be written to
     */
    public static File writeDocumentToXML(Document document, String path) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(format);
        if (!path.endsWith(".xml")) {
            path += ".xml";
        }
        File file = new File(path);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            writer.setOutputStream(fos);
            writer.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Show a dialog to select a file to load, or set the default file name to save
     * to.
     * 
     * @param parentComponent the parent component of the dialog
     * @param windowTitle     the title of the dialog
     * @param action          the action to perform
     * @return the file to load or save to
     */
    public File showFileChooser(JComponent parentComponent, String windowTitle, FileAction action) {
        // Create file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(windowTitle);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        // Add a filter to only show/allow .xml files
        FileFilter xmlFilter = new FileFilter() {
            @Override
            public boolean accept(java.io.File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "XML files (*.xml)";
            }
        };
        fileChooser.setFileFilter(xmlFilter);
        // Show the file chooser as a open/save dialog
        int result = action == FileAction.SAVE ? fileChooser.showSaveDialog(parentComponent)
                : fileChooser.showOpenDialog(parentComponent);
        if (result == JFileChooser.APPROVE_OPTION) {
            // if the user selected a file, return it
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    /**
     * Opens a dialog to choose a name for a file to save to, and then saves the
     * 
     * @param <T>             the type of object to save
     * @param parentComponent the parent component of the dialog
     * @param windowTitle     the title of the dialog
     * @param factory         the xml factory for the type of object to save
     * @return the file that was saved
     */
    public <T> File saveObjectToXML(JComponent parentComponent, String windowTitle, XMLFactory<T> factory,
            T objectToSave) {
        String path = showFileChooser(parentComponent, windowTitle, FileAction.SAVE).getAbsolutePath();
        if (path != null) {
            try {
                return factory.createXML(path, objectToSave);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Opens a dialog to choose a file to load, parses it using the factory, and
     * then
     * returns the object.
     * 
     * @param <T>             the type of object to load
     * @param parentComponent the parent component of the dialog
     * @param windowTitle     the title of the dialog
     * @param factory         the xml factory for the type of object to load
     * @return the object loaded from the file
     */
    public <T> T loadObjectFromXML(JComponent parentComponent, String windowTitle, XMLFactory<T> factory) {
        try {
            FileInputStream xmlStream = new FileInputStream(
                    showFileChooser(parentComponent, windowTitle, FileAction.LOAD));
            if (xmlStream != null) {
                try {
                    return factory.createFromXML(xmlStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return null;
    }
}
