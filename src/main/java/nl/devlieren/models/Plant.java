package nl.devlieren.models;

public class Plant {
    private String id;
    private String scientific_name;
    private String description;
    private String bloom_time;
    private String height;
    private String width;
    private String sun_requirements;
    private String soil_type;
    private String water_needs;

    public Plant(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBloom_time() {
        return bloom_time;
    }

    public void setBloom_time(String bloom_time) {
        this.bloom_time = bloom_time;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getSun_requirements() {
        return sun_requirements;
    }

    public void setSun_requirements(String sun_requirements) {
        this.sun_requirements = sun_requirements;
    }

    public String getSoil_type() {
        return soil_type;
    }

    public void setSoil_type(String soil_type) {
        this.soil_type = soil_type;
    }

    public String getWater_needs() {
        return water_needs;
    }

    public void setWater_needs(String water_needs) {
        this.water_needs = water_needs;
    }
}
