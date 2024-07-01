package nl.devlieren;

import nl.devlieren.models.Plant;
import nl.devlieren.webservices.PlantService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PlantServiceTest {

    @Test
    public void testAddPlant() throws IOException {
        Plant plant = new Plant("1", "ScientificName", "Description", "BloomTime", "Height", "Width", "SunRequirements", "SoilType", "WaterNeeds");
        PlantService.addPlant(plant);

        Plant retrievedPlant = PlantService.getPlantById("1");
        assertNotNull(retrievedPlant, "Plant should be added and retrievable.");
        assertEquals("ScientificName", retrievedPlant.getScientific_name(), "Scientific name should match.");
    }

    @Test
    public void testUpdatePlant() throws IOException {
        Plant plant = new Plant("1", "ScientificName", "Description", "BloomTime", "Height", "Width", "SunRequirements", "SoilType", "WaterNeeds");
        PlantService.addPlant(plant);

        Plant updatedPlant = new Plant("1", "UpdatedScientificName", "UpdatedDescription", "UpdatedBloomTime", "UpdatedHeight", "UpdatedWidth", "UpdatedSunRequirements", "UpdatedSoilType", "UpdatedWaterNeeds");
        PlantService.updatePlant("1", updatedPlant);

        Plant retrievedPlant = PlantService.getPlantById("1");
        assertNotNull(retrievedPlant, "Plant should be retrievable after update.");
        assertEquals("UpdatedScientificName", retrievedPlant.getScientific_name(), "Updated scientific name should match.");
    }

    @Test
    public void testRemovePlant() throws IOException {
        Plant plant = new Plant("1", "ScientificName", "Description", "BloomTime", "Height", "Width", "SunRequirements", "SoilType", "WaterNeeds");
        PlantService.addPlant(plant);

        PlantService.removePlant("1");

        Plant retrievedPlant = PlantService.getPlantById("1");
        assertNull(retrievedPlant, "Plant should be removed and not retrievable.");
    }
}
