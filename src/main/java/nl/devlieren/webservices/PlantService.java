package nl.devlieren.webservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.devlieren.models.Plant;

import java.io.File;
import java.util.List;

public class PlantResource {
    private static List<Plant> plants;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File jsonFile = new File("C:/Users/joshu/OneDrive - Stichting Hogeschool Utrecht/SD Minor/IPASS/GroeneDuim/src/main/resources/plant-info.json");
            System.out.println("File path: " + jsonFile.getAbsolutePath()); // Print absolute path for debugging
            plants = mapper.readValue(jsonFile, mapper.getTypeFactory().constructCollectionType(List.class, Plant.class));
            System.out.println("Plants loaded: " + plants.size()); // Print number of plants loaded
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load plants from JSON file: " + e.getMessage());
        }
    }

    public static Plant getPlantById(String id) {
        System.out.println("Fetching plant by ID: " + id);
        if (plants == null) {
            System.out.println("Plants list is null");
            return null;
        }

        for (Plant plant : plants){
            if (plant.getId().equals(id)){
                return plant;
            }
        }
        System.out.println("Plant is not in list");
        return null;
    }
}
