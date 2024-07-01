package nl.devlieren.webservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.devlieren.models.GenerateQRCode;
import nl.devlieren.models.Plant;

import javax.annotation.security.RolesAllowed;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlantService {
    private static List<Plant> plants;
    private static File jsonFile = new File("/opt/tomcat/plant-info.json");

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("File path: " + jsonFile.getAbsolutePath());
            plants = mapper.readValue(jsonFile, mapper.getTypeFactory().constructCollectionType(List.class, Plant.class));
            System.out.println("Plants loaded: " + plants.size());
            generateQRCodes();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load plants from JSON file: " + e.getMessage());
        }
    }

    public static void generateQRCodes(){
        for (Plant plant : plants){
            GenerateQRCode.generateQRCode(plant.getId());
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

    private static boolean plantExists(String id) {
        return getPlantById(id) != null;
    }

    public static void addPlant(Plant newPlant) throws IOException {
        if (plantExists(newPlant.getId())) {
            throw new IllegalArgumentException("Plant with ID " + newPlant.getId() + " already exists.");
        }
        plants.add(newPlant);
        savePlantsToFile();
        GenerateQRCode.generateQRCode(newPlant.getId());
        byte[] zipFileBytes = GenerateQRCode.generateZipFile();
    }

    private static void savePlantsToFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, plants);
    }

    public static void updatePlant(String id, Plant updatedPlant) throws IOException {
        Plant existingPlant = getPlantById(id);
        if (existingPlant == null) {
            throw new IllegalArgumentException("Plant with ID " + id + " does not exist.");
        }

        existingPlant.setScientific_name(updatedPlant.getScientific_name());
        existingPlant.setDescription(updatedPlant.getDescription());
        existingPlant.setBloom_time(updatedPlant.getBloom_time());
        existingPlant.setHeight(updatedPlant.getHeight());
        existingPlant.setWidth(updatedPlant.getWidth());
        existingPlant.setSun_requirements(updatedPlant.getSun_requirements());
        existingPlant.setSoil_type(updatedPlant.getSoil_type());
        existingPlant.setWater_needs(updatedPlant.getWater_needs());

        savePlantsToFile();
    }

    public static void removePlant(String id) throws IOException {
        Plant plantToRemove = getPlantById(id);
        if (plantToRemove == null) {
            throw new IllegalArgumentException("Plant with ID " + id + " does not exist.");
        }
        System.out.println("Removing " + plantToRemove.getId());

        plants.remove(plantToRemove);
        savePlantsToFile();

        deleteQRCodeFile(plantToRemove.getId());
    }

    private static void deleteQRCodeFile(String plantId) {
        File qrCodeFile = new File("/opt/tomcat/qrcodes/" + plantId + ".png");
        if (qrCodeFile.exists()) {
            if (qrCodeFile.delete()) {
                System.out.println("Deleted QR code for plant ID: " + plantId);
            } else {
                System.err.println("Failed to delete QR code for plant ID: " + plantId);
            }
        } else {
            System.err.println("QR code file not found for plant ID: " + plantId);
        }
    }

    //Load plants for testing purposes
    public static void loadPlants() throws IOException {
        plants = new ArrayList<>();
        savePlantsToFile();
    }

}