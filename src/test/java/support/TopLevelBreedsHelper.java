package support;

import java.io.FileReader;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONTokener;

public class TopLevelBreedsHelper {
    private static final String DATASET_BREEDS_FILE = "data_sets/dataset_breeds.json";
    private static Set<String> topLevelBreeds = null;

    public static void loadTopLevelBreeds() {
        if (topLevelBreeds != null) return;
        topLevelBreeds = new HashSet<>();
        try (FileReader reader = new FileReader(DATASET_BREEDS_FILE)) {
            JSONObject obj = new JSONObject(new JSONTokener(reader));
            for (String breed : obj.keySet()) {
                topLevelBreeds.add(breed);
            }
        } catch (Exception e) {
            topLevelBreeds = new HashSet<>();
        }
    }

    public static Set<String> getTopLevelBreeds() {
        loadTopLevelBreeds();
        return topLevelBreeds;
    }

    public static boolean isTopLevelBreed(String breed) {
        loadTopLevelBreeds();
        return topLevelBreeds.contains(breed);
    }
}
