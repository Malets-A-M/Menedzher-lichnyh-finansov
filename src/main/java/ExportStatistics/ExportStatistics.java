package ExportStatistics;

import ImportExpenses.ImportExpenses;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExportStatistics {
    private MaxCategory maxCategory = new MaxCategory();
    private Gson gson = new Gson();
    private File file = new File("statistics.json");
    private Writer writer = Files.newBufferedWriter(Paths.get("statistics.json"));
    private JsonObject object = new JsonObject();

    public ExportStatistics() throws IOException {
    }

    public ExportStatistics addJSON(MaxCategory category, ImportExpenses importExpenses, String date, String statisticName){
        object.add(statisticName, category.max(importExpenses, date));
        return this;
    }

    public ExportStatistics writer() throws Exception {
        if (!file.exists()) file.createNewFile();
        gson.toJson(object, writer);
        writer.close();
        String fileWrite = convertFileIntoString("statistics.json");
        System.out.println(fileWrite);
        return this;
    }
    public static String convertFileIntoString(String file)throws Exception {
        String result;
        result = new String(Files.readAllBytes(Paths.get(file)));
        return result;
    }
}
