package academy.learnprograrmming.rest;

import academy.learnprogramming.entity.User;
import academy.learnprogramming.service.SecurityUtil;
import academy.learnprogramming.service.TodoService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 
 * @author jose casal
 */
@Path("user")
public class UserRest {

    @Inject
    private SecurityUtil securityUtil;
    @Context
    private UriInfo uriInfo;
    @Inject
    private TodoService todoService;


    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@NotNull @FormParam("email") String email,
                          @NotNull @FormParam("password") String password) {

        //Authenticate user
        //Generate token
        //Return token in Response header to client

        boolean authenticated = securityUtil.authenticateUser(email, password);
        if (!authenticated) {
            throw new SecurityException("Email or password not valid");

        }

        String token = generateToken(email);

        return Response.ok().header(HttpHeaders.AUTHORIZATION, SecurityUtil.BEARER + " " + token).build();
    }

    private String generateToken(String email) {
        Key securityKey = securityUtil.getSecurityKey();
       return Jwts.builder().setSubject(email).setIssuedAt(new Date()).setIssuer(uriInfo.getBaseUri().toString())
                .setAudience(uriInfo.getAbsolutePath().toString())
                .setExpiration(securityUtil.toDate(LocalDateTime.now().plusMinutes(15)))
                .signWith(SignatureAlgorithm.HS512, securityKey).compact();


    }


    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(@NotNull User user) {
        todoService.saveUser(user);

        return Response.ok(user).build();

    }



}
