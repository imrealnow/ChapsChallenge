package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import nz.ac.vuw.ecs.swen225.gp22.app.ActionController;
import nz.ac.vuw.ecs.swen225.gp22.app.App;
import nz.ac.vuw.ecs.swen225.gp22.app.Bindings;
import nz.ac.vuw.ecs.swen225.gp22.persistence.XMLFactory;
import nz.ac.vuw.ecs.swen225.gp22.persistence.XMLSerializer;
import nz.ac.vuw.ecs.swen225.gp22.util.Time;
import nz.ac.vuw.ecs.swen225.gp22.util.observer.Observer;

public class LevelRecorder implements Observer<ActionController, Bindings>{

    private LevelReplay currentLevelInProgress = new LevelReplay();

    /**
     * TOOD: Add Javadoc
     */
    public LevelRecorder() {
        App.INSTANCE.getController().addObserver(this);
      }

    @Override
    public void notify(ActionController t, Bindings r) {
        ActionRecord action = new ActionRecord(r, Time.INSTANCE.getTimeSinceStart();
        currentLevelInProgress.add(action);
        
    }

    public void saveCurrentReplay(){
        XMLFactory<LevelReplay> recordingFactory = new XMLFactory<LevelReplay>() {
            @Override
            public LevelReplay createFromXML(File xmlFile) throws DocumentException {
                SAXReader reader = new SAXReader();
                Document document = reader.read(xmlFile);
                Element actions = document.getRootElement();
                List<ActionRecord> actionList = new ArrayList<>();
                // read values from each action element
                for (Element action : actions.elements("action")) {
                    Long time = Long.parseLong(action.attributeValue("time"));
                    String bindings = action.attributeValue("type");
                    Bindings bindingType;
                    switch (bindings) {
                    case "Down":
                    bindingType = Bindings.Down;
                    break;
                    case "Up":
                    bindingType = Bindings.Up;
                    break;
                    case "Left":
                    bindingType = Bindings.Left;
                    break;
                    case "Right":
                    bindingType = Bindings.Right;
                    break;
                    }
                    ActionRecord actionObject = new ActionRecord(bindingType, time);
                    actionList.add(actionObject);
                }
                // create and return recording
                return new LevelReplay(actionList);
            }
            @Override
            public File createXML(String filePath, LevelReplay objectToSave) throws IOException {
                // create document
                Document document = DocumentHelper.createDocument();
                Element root = document.addElement("actions");
                // add each action to the actions element
                objectToSave.getActions().forEach(action -> {
                    Element actionElement = root.addElement("action");
                    actionElement.addAttribute("type", action.getAction().name());
                    actionElement.addAttribute("time", String.valueOf(action.getTime()));
                });
                // setup xml writer
                OutputFormat format = OutputFormat.createPrettyPrint();
                format.setEncoding("UTF-8");
                XMLWriter writer = new XMLWriter(format);
                if(!filePath.endsWith(".xml")) {
                    filePath += ".xml";
                }
                // write to file
                File file = new File(path);
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    writer.setOutputStream(fos);
                    writer.write(document);
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
                return file;
            }
        };
    
        // save and load recording
        XMLSerializer serializer = new XMLSerializer();
        File file = serializer.saveObjectToXML(parentJFrame, "Save recording", recordingFactory);
        if (file != null) {
            try {
                LevelReplay recording = recordingFactory.createFromXML(file);
                // do something with the recording
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
    
}
