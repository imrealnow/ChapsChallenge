package nz.ac.vuw.ecs.swen225.gp22.recorder;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import nz.ac.vuw.ecs.swen225.gp22.persistence.ElementFactory;

public class ActionRecordFactory implements ElementFactory<ActionRecord>{

    @Override
    public Element createElement(ActionRecord record) {
        Element root = DocumentHelper.createElement("ActionRecord");
        root.addAttribute("action", ActionRecord.getActionKey(record.getAction()));
        root.addAttribute("time", Integer.toString(record.getTime()));
        return root;
    }

    @Override
    public ActionRecord createFromElement(Element element) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
