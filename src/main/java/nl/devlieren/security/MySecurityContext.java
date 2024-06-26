package nl.devlieren.security;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private final User user;
    private final String scheme;
    private final String role;

    public MySecurityContext(User user, String scheme, String role) {
        this.user = user;
        this.scheme = scheme;
        this.role = role;
    }

    @Override
    public Principal getUserPrincipal() {
        return this.user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return s.equals(this.role);
    }

    @Override
    public boolean isSecure() {
        return "https".equals(this.scheme);
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}