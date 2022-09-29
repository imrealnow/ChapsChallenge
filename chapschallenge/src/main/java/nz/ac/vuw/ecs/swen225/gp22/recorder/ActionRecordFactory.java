package nz.ac.vuw.ecs.swen225.gp22.recorder;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

import nz.ac.vuw.ecs.swen225.gp22.app.Bindings;
import nz.ac.vuw.ecs.swen225.gp22.persistence.ElementFactory;

/**
 * TODO: Add Javadoc
 */
public class ActionRecordFactory implements ElementFactory<ActionRecord> {
    // can be used to find:
    // the enum value from the string
    // or the string from the enum value
    private static final BiMap<Bindings, String> bindingsMap = ImmutableBiMap.of(
            Bindings.Up, "Up",
            Bindings.Down, "Down",
            Bindings.Left, "Left",
            Bindings.Right, "Right");

    @Override
    public Element createElement(ActionRecord record) {
        Element root = DocumentHelper.createElement("ActionRecord");
        root.addAttribute("action", bindingsMap.get(record.getAction()));
        root.addAttribute("time", Long.toString(record.getTime()));
        return root;
    }

    @Override
    public ActionRecord createFromElement(Element element) {
        Bindings action = bindingsMap.inverse().get(element.attributeValue("action"));
        Long time = Long.parseLong(element.attributeValue("time"));
        return new ActionRecord(action, time);
    }

}
