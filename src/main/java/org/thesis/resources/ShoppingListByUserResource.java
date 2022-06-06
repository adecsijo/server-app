package org.thesis.resources;

import io.quarkus.security.Authenticated;
import org.thesis.dtos.ShoppingListByUserDto;
import org.thesis.dtos.SimpleStringDto;
import org.thesis.dtos.UnitDto;
import org.thesis.exceptions.SimpleException;
import org.thesis.services.ShoppingListByUserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Arrays;

@Authenticated
@Path("/shoppingListByUser")
public class ShoppingListByUserResource {

    @Inject
    ShoppingListByUserService shoppingListByUserService;

    @GET
    @RolesAllowed({"basic", "admin"})
    public Response getAllShoppingListByUser(@Context SecurityContext securityContext) {
        try {
            return Response.ok(shoppingListByUserService.getAllShoppingListByUser(securityContext.getUserPrincipal().getName())).build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }

    @POST
    @RolesAllowed({"basic", "admin"})
    public Response addNewShoppingList(@Context SecurityContext securityContext, ShoppingListByUserDto shoppingListByUserDto) {
        try {
            shoppingListByUserService.addNewShoppingListByUser(securityContext.getUserPrincipal().getName(), shoppingListByUserDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }

    @PUT
    @RolesAllowed({"basic", "admin"})
    public Response modifyShoppingListByUser(@Context SecurityContext securityContext, ShoppingListByUserDto shoppingListByUserDto) {
        try {
            shoppingListByUserService.modifyShoppingListByUser(securityContext.getUserPrincipal().getName(), shoppingListByUserDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }

    @DELETE
    @RolesAllowed({"basic", "admin"})
    public Response deleteShoppingListByUser(@Context SecurityContext securityContext, ShoppingListByUserDto shoppingListByUserDto) {
        try {
            shoppingListByUserService.deleteShoppingListByUser(securityContext.getUserPrincipal().getName(), shoppingListByUserDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }
}
