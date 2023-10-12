package org.example;


import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsonable;

import java.io.IOException;
import java.io.Writer;

public class Executables implements Jsonable {

    private static String path;

    public Executables()
    {

    }
    public Executables(String path)
    {
        this.path = path;
    }
    //writes path to json file. yeah writing every directory to seperate json files is not efficient but its easier to debug imo 
    @Override
    public String toJson() {
        JsonObject json  = new JsonObject();
        json.put("path", this.path);

        return json.toJson();
    }

    @Override
    public void toJson(Writer writer) throws IOException {
        try {
            writer.write(this.toJson());
        } catch (Exception ignored) {
        }
    }
}
