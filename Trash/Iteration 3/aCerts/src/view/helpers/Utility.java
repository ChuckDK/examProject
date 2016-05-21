package view.helpers;

import javafx.scene.Node;
import javafx.scene.control.TabPane;

public class Utility
{
    //If you have a tab, you can find the parent tab pane the tab belongs to.
    //We use this method when we need to get the size of a window with a tab pane.
    public static TabPane getParentTabPane(Node node)
    {
        //Loop which calls the get parent method on a node. If it find the parent, it returns it.
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
