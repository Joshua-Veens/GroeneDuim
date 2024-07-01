package nl.devlieren.webservices;

import nl.devlieren.models.Plant;
import nl.devlieren.security.MySecurityContext;
import nl.devlieren.webservices.PlantService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
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
    @RolesAllowed("admin")
    public Response addPlant(@Context ContainerRequestContext context, Plant plant) {
        MySecurityContext securityContext = (MySecurityContext) context.getSecurityContext();
        System.out.println(securityContext.getUserPrincipal().getName() + " is adding plant: " + plant.getId());
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

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response updatePlant(@Context ContainerRequestContext context, @PathParam("id") String id, Plant updatedPlant) {
        MySecurityContext securityContext = (MySecurityContext) context.getSecurityContext();
        System.out.println(securityContext.getUserPrincipal().getName() + " is updating plant: " + id);
        try {
            PlantService.updatePlant(id, updatedPlant);
            return Response.ok(updatedPlant).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update plant").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response deletePlant(@Context ContainerRequestContext context, @PathParam("id") String id) {
        MySecurityContext securityContext = (MySecurityContext) context.getSecurityContext();
        System.out.println(securityContext.getUserPrincipal().getName() + " is removing plant: " + id);
        try {
            PlantService.removePlant(id);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to remove plant").build();
        }
    }
}