package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import nz.ac.vuw.ecs.swen225.gp22.app.Bindings;
import nz.ac.vuw.ecs.swen225.gp22.persistence.XMLFactory;
import nz.ac.vuw.ecs.swen225.gp22.persistence.XMLSerializer;

import org.dom4j.DocumentException;

public class LevelReplayFactory implements XMLFactory<LevelReplay> {
    private static final ActionRecordFactory actionFactory = new ActionRecordFactory();

    @Override
    public LevelReplay createFromXML(InputStream xmlFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlFile);
        Element actions = document.getRootElement();
        int levelIndex = Integer.parseInt(actions.attributeValue("levelIndex"));
        List<ActionRecord> actionList = new ArrayList<>();
        // read values from each action element
        for (Element action : actions.elements()) {
            actionList.add(actionFactory.createFromElement(action));
        }
        // create and return recording
        return new LevelReplay(levelIndex, actionList);
    }

    @Override
    public File createXML(String filePath, LevelReplay objectToSave) throws IOException {
        // create document
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("actions");
        root.addAttribute("LevelIndex", Integer.toString(objectToSave.getLevelIndex()));
        // add each action to the actions element
        objectToSave.getActions().forEach(action -> {
            root.add(actionFactory.createElement(action));
        });
        String fileName = filePath + File.separator + "Level-" + objectToSave.getLevelIndex() + "-replay-"
                + new Date().getTime() + ".xml";
        return XMLSerializer.writeDocumentToXML(document, filePath);
    }
}
