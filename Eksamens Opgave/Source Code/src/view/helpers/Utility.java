package view.helpers;

import javafx.scene.Node;
import javafx.scene.control.TabPane;

public class Utility
{
    //If you have a tab, you can find the parent tab pane the tab belongs to.
    //We use this method when we need to get the size of a window with a tab pane.
    //this method is based on the solution found at:
    // http://stackoverflow.com/questions/33186784/javafx-get-access-to-a-tab-or-tabpane-from-a-tabcontentnode    29/05-2016
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
