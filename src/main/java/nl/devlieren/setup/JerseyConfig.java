package nl.devlieren.setup;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("nl.devlieren.webservices, nl.devlieren.security");
        register(RolesAllowedDynamicFeature.class);
        register(JacksonFeature.class);
    }
}