package ExportStatistics;

import ImportExpenses.ImportExpenses;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExportStatistics {
    private MaxCategory maxOfCategory = new MaxCategory();
    private Gson gson = new Gson();
    private File file = new File("maxCategory.json");

    public void maxCategory(ImportExpenses ImportExpenses) throws IOException {
        if (!file.exists()) file.createNewFile();
        for (String title : ImportExpenses.getExpensesMap().keySet()){
            if (maxOfCategory.getSum() == 0){
                maxOfCategory.setCategory(title);
                maxOfCategory.setSum(ImportExpenses.getExpensesMap().get(title));
            } else if (maxOfCategory.getSum() < ImportExpenses.getExpensesMap().get(title)) {
                maxOfCategory.setCategory(title);
                maxOfCategory.setSum(ImportExpenses.getExpensesMap().get(title));
            }
        }
        Writer writer = Files.newBufferedWriter(Paths.get("maxCategory.json"));
        JsonObject object = new JsonObject();
        JsonObject maxCategory = new JsonObject();
        maxCategory.addProperty("category", maxOfCategory.getCategory());
        maxCategory.addProperty("sum", maxOfCategory.getSum());
        object.add("maxCategory", maxCategory);
        gson.toJson(object, writer);
        writer.close();
        System.out.println(maxOfCategory);
    }
}
