package GUI.PaneManager;

import GUI.Interfaces.Resizable;

import java.util.ArrayList;

/*this class serves as a way for all the view to access other views for purposes such as changing to another view or
manipulate the other views. This class limits the uses of the arraylist to adding panes and getting panes.
 */

public class PaneManager
{
    private static ArrayList<Resizable> panes = new ArrayList<>();

    public static void addPane(Resizable pane)
    {
        panes.add(pane);
    }


    //throws exception so that errors would be manageable from another class
    public static Resizable getPane(int index) throws Exception
    {
        try
        {
            return panes.get(index);
        }
        catch (Exception ex)
        {
            throw new Exception("Pane not available");
        }
    }
}
