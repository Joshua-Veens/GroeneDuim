package nl.devlieren.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;
import java.util.Calendar;
import java.util.Map;

@Path("authentication")
public class AuthenticationResource {
    public static final Key key = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response authenticateUser(LogonRequest logonRequest) {
        try {
            String role = User.validateLogin(logonRequest.username, logonRequest.password);
            String token = createToken(logonRequest.username, role);
            return Response.ok(Map.of("JWT", token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/validate")
    @RolesAllowed("admin")
    public Response validateToken(@Context MySecurityContext securityContext, @HeaderParam(HttpHeaders.AUTHORIZATION) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer".length()).trim();
            try {
                JwtParser parser = Jwts.parser().setSigningKey(key);
                Claims claims = parser.parseClaimsJws(token).getBody();
                String username = claims.getSubject();
                String role = claims.get("role", String.class);
                User user = User.getUserByName(username);

                if (claims.getExpiration().before(Calendar.getInstance().getTime())) {
//                    System.out.println("Token has expired");
                    return Response.status(Response.Status.UNAUTHORIZED).entity(Map.of("error", "Token has expired")).build();
                }
                if (user != null) {
//                    System.out.println("Succes!");
                    return Response.ok(Map.of("username", username, "role", role)).build();
                } else {
//                    System.out.println("Invalid token");
                    return Response.status(Response.Status.UNAUTHORIZED).entity(Map.of("error", "Invalid token")).build();
                }
            } catch (JwtException | IllegalArgumentException e) {
//                System.out.println("Invalid token");
                return Response.status(Response.Status.UNAUTHORIZED).entity(Map.of("error", "Invalid token")).build();
            }
        } else {
//            System.out.println("Authorization header must be provided");
            return Response.status(Response.Status.UNAUTHORIZED).entity(Map.of("error", "Authorization header must be provided")).build();
        }
    }

    private String createToken(String username, String role) throws JwtException {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 60);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}
