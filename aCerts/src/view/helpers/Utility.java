package view.helpers;

import javafx.scene.Node;
import javafx.scene.control.TabPane;

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
