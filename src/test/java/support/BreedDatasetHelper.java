package support;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.*;

public class BreedDatasetHelper {
    public static Map<String, List<String>> getAllImagesMapForBreedContaining(String term) {
        loadDataset();
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : breedImagesMap.entrySet()) {
            if (entry.getKey().toLowerCase().contains(term.toLowerCase())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }
    public static List<String> getImagesForBreedContaining(String term) {
        loadDataset();
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : breedImagesMap.entrySet()) {
            if (entry.getKey().toLowerCase().contains(term.toLowerCase())) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }
    private static void debugLogBreeds(List<String> validBreeds, String chosen) {
        try (FileWriter fw = new FileWriter("reports/debug_breeds.txt", false)) {
            fw.write("Valid breeds:\n");
            for (String b : validBreeds) fw.write(b + "\n");
            fw.write("Chosen: " + chosen + "\n");
        } catch (Exception e) { /* ignore */ }
    }
    private static final String DATASET_FILE = "data_sets/dataset_images.json";
    private static Map<String, List<String>> breedImagesMap = null;
    private static String lastRandomBreed = null;
    private static List<String> lastRandomBreedImages = null;

    private static void loadDataset() {
        if (breedImagesMap != null) return;
        breedImagesMap = new HashMap<>();
        try (FileReader reader = new FileReader(DATASET_FILE)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode obj = mapper.readTree(reader);
            Iterator<String> fieldNames = obj.fieldNames();
            while (fieldNames.hasNext()) {
                String breed = fieldNames.next();
                JsonNode imagesNode = obj.get(breed);
                List<String> images = new ArrayList<>();
                for (JsonNode img : imagesNode) {
                    images.add(img.asText());
                }
                breedImagesMap.put(breed, images);
            }
        } catch (Exception e) {
            breedImagesMap = new HashMap<>();
        }
    }

    public static String getRandomBreed() {
        loadDataset();
        Response apiResponse = RestAssured.get("https://dog.ceo/api/breeds/list/all");
        if (apiResponse.getStatusCode() != 200) return null;
        String apiJson = apiResponse.asString();
        List<String> validBreeds = new ArrayList<>();
        Map<String, String> datasetToApi = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(apiJson);
            JsonNode messageNode = root.get("message");
            for (String key : breedImagesMap.keySet()) {
                String breedName = extractBreedName(key); // ex: Scotch_terrier
                String[] parts = breedName.split("_");
                String apiBreed = null;
                String apiSubBreed = null;
                if (parts.length == 2) {
                    apiSubBreed = parts[0].toLowerCase();
                    apiBreed = parts[1].toLowerCase();
                    if (apiBreed.equals("terrier") && apiSubBreed.equals("scotch")) apiSubBreed = "scottish";
                    if (apiBreed.equals("terrier") && apiSubBreed.equals("west")) apiSubBreed = "westhighland";
                    if (apiBreed.equals("terrier") && apiSubBreed.equals("kerry")) apiSubBreed = "kerryblue";
                    if (apiBreed.equals("terrier") && apiSubBreed.equals("dandie")) apiSubBreed = "dandie";
                    if (apiBreed.equals("terrier") && apiSubBreed.equals("fox")) apiSubBreed = "fox";
                    if (apiBreed.equals("spaniel") && apiSubBreed.equals("brittany")) apiSubBreed = "brittany";
                    if (apiBreed.equals("spaniel") && apiSubBreed.equals("blenheim")) apiSubBreed = "blenheim";
                } else if (parts.length == 1) {
                    apiBreed = breedName.toLowerCase();
                }
                if (apiBreed != null && messageNode.has(apiBreed)) {
                    JsonNode subBreeds = messageNode.get(apiBreed);
                    if (apiSubBreed == null || subBreeds == null || !subBreeds.isArray() || subBreeds.size() == 0) {
                        validBreeds.add(apiBreed);
                        datasetToApi.put(key, apiBreed);
                    } else {
                        for (JsonNode sub : subBreeds) {
                            if (apiSubBreed != null && sub.asText().equals(apiSubBreed)) {
                                validBreeds.add(apiBreed + "/" + apiSubBreed);
                                datasetToApi.put(key, apiBreed + "/" + apiSubBreed);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            debugLogBreeds(validBreeds, null);
            return null;
        }
        if (validBreeds.isEmpty()) {
            debugLogBreeds(validBreeds, null);
            return null;
        }
        Random rand = new Random();
        String chosen = validBreeds.get(rand.nextInt(validBreeds.size()));
        debugLogBreeds(validBreeds, chosen);
        String datasetKey = null;
        for (Map.Entry<String, String> entry : datasetToApi.entrySet()) {
            if (entry.getValue().equals(chosen)) {
                datasetKey = entry.getKey();
                break;
            }
        }
        lastRandomBreed = datasetKey;
        lastRandomBreedImages = breedImagesMap.get(datasetKey);
        try (FileWriter fw = new FileWriter("reports/last_breedimages_breed.txt", false)) {
            fw.write(chosen + "\n");
        } catch (Exception e) { /* ignore */ }
        return chosen;
    }

    public static String extractBreedName(String datasetKey) {
        if (datasetKey == null) return null;
        if (datasetKey.length() > 10) {
            return datasetKey.substring(10);
        }
        return datasetKey;
    }

    public static String toApiBreedName(String breedName) {
        if (breedName == null) return null;
        return breedName.toLowerCase().replace("_", "-");
    }

    public static List<String> getImagesForBreed(String breed) {
        loadDataset();
        if (breed == null) return Collections.emptyList();
        List<String> images = new ArrayList<>();
        String breedLower = breed.toLowerCase();
        for (String key : breedImagesMap.keySet()) {
            String extracted = extractBreedName(key).toLowerCase();
            if (extracted.equals(breedLower) || extracted.contains(breedLower) || breedLower.contains(extracted)) {
                images.addAll(breedImagesMap.get(key));
            }
        }
        return images;
    }

    public static boolean isValidImageForBreed(String breed, String imageUrl) {
        List<String> images = getImagesForBreed(breed);
        for (String img : images) {
            if (imageUrl.endsWith("/" + img)) return true;
        }
        return false;
    }

    public static boolean isAnyValidImage(String imageUrl) {
        loadDataset();
        for (String breed : breedImagesMap.keySet()) {
            if (isValidImageForBreed(breed, imageUrl)) {
                return true;
            }
        }
        return false;
    }

    public static String getLastRandomBreed() {
        return lastRandomBreed;
    }

    public static List<String> getLastRandomBreedImages() {
        return lastRandomBreedImages;
    }
}
