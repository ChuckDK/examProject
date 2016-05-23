package control.settings;

import java.io.*;
import java.nio.file.Paths;

public class SettingsInterface
{
    public static Object readObject(String filename) throws Exception
    {
        Object settingsFile = null;
        ObjectInputStream in = null;
        try{
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(Paths.get("").toAbsolutePath().toString()+"/"+filename)));
            settingsFile = in.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                in.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return settingsFile;
    }

    public static void writeObject(String filename, Object settingsFile) throws Exception
    {
        ObjectOutputStream out = null;

        try
        {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            out.writeObject(settingsFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                out.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
