package nl.devlieren.webservices;

import nl.devlieren.models.Plant;
import nl.devlieren.webservices.PlantService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/plants")
public class PlantResource {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlantById(@PathParam("id") String id) {
        Plant plant = PlantService.getPlantById(id);
        if (plant != null) {
            System.out.println("Plant found: " + plant.getId());
            return Response.ok(plant).build();
        } else {
            System.out.println("No plant found");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlant(Plant plant) {
        try {
            PlantService.addPlant(plant);
            return Response.status(Response.Status.CREATED).entity(plant).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to save plant").build();
        }
    }
}