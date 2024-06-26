package nl.devlieren.controllers;

import nl.devlieren.models.Plant;
import nl.devlieren.webservices.PlantResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/plants")
public class PlantController {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlantById(@PathParam("id") String id) {
        Plant plant = PlantResource.getPlantById(id);
        if (plant != null) {
            System.out.println("Plant found: " + plant.getId());
            return Response.ok(plant).build();
        } else {
            System.out.println("Error");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}