package academy.learnprograrmming.rest;

import academy.learnprogramming.service.SecurityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;
import java.security.Principal;

/**
 * 
 * @author jose casal
 */
@Provider
@Authz
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    @Inject
    private SecurityUtil securityUtil;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //Grab token from the header of the request using the AUTHORIZATION constant
        //Throw an exception with a message if there's no token
        //Parse the token
        //If parsing succeeds, proceed
        //Otherwise we throw an exception with message

        String authString = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authString == null || authString.isEmpty() || !authString.startsWith("Bearer")) {


            //Beautify this exception
            throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        }

        String token = authString.substring(SecurityUtil.BEARER.length()).trim();

        try {
            Key key = securityUtil.getSecurityKey(); //Some Security Key
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            SecurityContext originalContext = requestContext.getSecurityContext();

            requestContext.setSecurityContext(new SecurityContext() {
                            @Override
                            public Principal getUserPrincipal() {
                                return new Principal() {
                                    @Override
                        public String getName() {
                            return claimsJws.getBody().getSubject();
                        }
                    };
                }

                @Override
                public boolean isUserInRole(String role) {
                    return originalContext.isUserInRole(role);
                }

                @Override
                public boolean isSecure() {
                    return originalContext.isSecure();
                }

                @Override
                public String getAuthenticationScheme() {
                    return originalContext.getAuthenticationScheme();
                }
            });



        } catch (Exception e) {
           requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

        }

    }
}

