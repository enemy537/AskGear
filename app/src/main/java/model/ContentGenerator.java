package model;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Pedro on 10/03/2016.
 * This class define an object to easy convert data from JSON to Curiosity objects.
 */
public class ContentGenerator {
    private InputStream is;
    private String json;
    private Gson gson;

    /**
      *  Class constructor
      *  @param is InputStream with the path of JSON file
      *  @throws IOException if the file or path is wrong
     */
    public ContentGenerator(InputStream is) throws IOException{
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        gson = new Gson();
    }

    /**
     * Method converts JSON notation to Curiosity objects
     * @return List<Curiosity> with all objects contained in JSON file
     */
    public List<Curiosity> getContentList(){
        Type deserialized = new TypeToken<List<Curiosity>>(){}.getType();
        List<Curiosity> received = gson.fromJson(json, deserialized);
        return received;
    }
}
