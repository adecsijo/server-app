package org.thesis.resources;

import org.thesis.dtos.SimpleStringDto;
import org.thesis.dtos.UserDto;
import org.thesis.exceptions.SimpleException;
import org.thesis.services.UserService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Arrays;

@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @PermitAll
    @Path("/registration")
    public Response registration(UserDto userDto) {
        try {
            userService.registration(userDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        }
    }

    @POST
    @PermitAll
    @Path("/login")
    public Response login(UserDto userDto) {
        try {
            return Response.ok(userService.login(userDto)).build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        }
    }

    @PUT
    @RolesAllowed({"basic", "admin"})
    public Response modifyUser(@Context SecurityContext securityContext, UserDto userDto) {
        try {
            return Response.ok(userService.modifyUser(securityContext.getUserPrincipal().getName(), userDto)).build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        }
    }
}
