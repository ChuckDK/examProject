package control.settings;

import java.io.*;
import java.nio.file.Paths;

public class FileIO
{
    //Method which will be called upon from other classes to read files with object input stream.
    public static Object readObject(String filename) throws Exception
    {
        //An object initialisation which have to be here in order to render the method able to be used upon an object.
        Object settingsFile = null;

        //The stream must be set to be null from the start when it are initialized.
        ObjectInputStream in = null;

        //Streaming should be done in a try-catch as it may trigger an exception.
        try{
            //The object input stream which has the lover types
            //of input streams and eventually the path of the file in the file input stream.
            //
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(Paths.get("").toAbsolutePath().toString()+"/"+filename)));
            settingsFile = in.readObject();
        }
        //Handling any exceptions that may occur.
        catch(Exception e){
            e.printStackTrace();
        }
        //Finally, closing the stream, also using a try catch vs. exception.
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

    //Method which will be called upon from other classes to write files with object output stream.
    public static void writeObject(String filename, Object file) throws Exception
    {
        ObjectOutputStream out = null;

        try
        {
            //The object output stream which has the lover types of output
            //streams, writes the result to a new created file with the filename provided in the method scope.
            //What it writes, is an object.
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            out.writeObject(file);
        }

        //Handling any exceptions that may occur.
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Finally, closing the stream, also using a try catch vs. exception.
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
