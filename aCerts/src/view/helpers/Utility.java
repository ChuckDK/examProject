package view.helpers;

import javafx.scene.Node;
import javafx.scene.control.TabPane;

/**
 * Created by dennis on 5/13/16.
 */
public class Utility
{
    public static TabPane getParentTabPane(Node node)
    {
        for(node = node.getParent(); node != null; node = node.getParent())
        {
            if(node instanceof TabPane)
            {
                return (TabPane) node;
            }
        }
        return null;
    }
}
