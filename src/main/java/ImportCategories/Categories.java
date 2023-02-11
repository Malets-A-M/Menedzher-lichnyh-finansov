package ImportCategories;

import java.io.*;
import java.util.HashMap;

public class Categories {
    private static Categories instance;
    private File file = new File("categories.tsv");
    private BufferedReader reader;
    private String line;
    private String[] lineSplit;
    private HashMap<String, String> categories = new HashMap<>();

    private Categories() throws IOException {
        importCategories();
    }

    public void importCategories() throws IOException {
        reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null){
            lineSplit = line.split("\t");
            categories.put(lineSplit[0], lineSplit[1]);
        }
        reader.close();
        System.out.println(categories);
    }
    public static Categories getInstance() throws IOException {
        if (instance == null) instance = new Categories();
        return instance;
    }


    public HashMap<String, String> getCategories() {
        return categories;
    }

    public void setCategories(HashMap<String, String> categories) {
        this.categories = categories;
    }
}
