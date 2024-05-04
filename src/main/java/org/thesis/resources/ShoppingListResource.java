package org.thesis.resources;

import io.quarkus.security.Authenticated;
import org.thesis.dtos.ShoppingListDto;
import org.thesis.exceptions.SimpleException;
import org.thesis.services.ShoppingListService;

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

@Authenticated
@Path("/shoppingListByUser")
public class ShoppingListResource {

    @Inject
    ShoppingListService shoppingListService;

    @GET
    @RolesAllowed({"basic", "admin"})
    public Response getAllShoppingListByUser(@Context SecurityContext securityContext) {
        try {
            return Response.ok(shoppingListService.getAllShoppingListByUser(securityContext.getUserPrincipal().getName())).build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(e.toString()).build();
        }
    }

    @POST
    @RolesAllowed({"basic", "admin"})
    public Response addNewShoppingList(@Context SecurityContext securityContext, ShoppingListDto shoppingListDto) {
        try {
            shoppingListService.addNewShoppingListByUser(securityContext.getUserPrincipal().getName(), shoppingListDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(e.toString()).build();
        }
    }

    @PUT
    @RolesAllowed({"basic", "admin"})
    public Response modifyShoppingListByUser(@Context SecurityContext securityContext, ShoppingListDto shoppingListDto) {
        try {
            shoppingListService.modifyShoppingListByUser(securityContext.getUserPrincipal().getName(), shoppingListDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(e.toString()).build();
        }
    }

    @DELETE
    @RolesAllowed({"basic", "admin"})
    public Response deleteShoppingListByUser(@Context SecurityContext securityContext, ShoppingListDto shoppingListDto) {
        try {
            shoppingListService.deleteShoppingListByUser(securityContext.getUserPrincipal().getName(), shoppingListDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(e.toString()).build();
        }
    }
}
