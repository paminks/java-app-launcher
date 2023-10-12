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
