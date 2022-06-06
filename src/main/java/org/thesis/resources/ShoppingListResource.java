package org.thesis.resources;

import io.quarkus.security.Authenticated;
import org.thesis.dtos.ItemDetailsDto;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Authenticated
@Path("/shoppingList")
public class ShoppingListResource {

    @Inject
    ShoppingListService shoppingListService;

    @GET
    @RolesAllowed({"basic", "admin"})
    @Path("/{listId}")
    public Response getShoppingListById(@PathParam("listId") Integer listId) {
        try {
            return Response.ok(shoppingListService.getShoppingListById(listId)).build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

    @POST
    @RolesAllowed({"basic", "admin"})
    @Path("/{listId}")
    public Response addItemToList(@PathParam("listId") Integer listId, ItemDetailsDto itemDetailsDto) {
        try {
            shoppingListService.addItemToList(listId, itemDetailsDto);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

    @PUT
    @RolesAllowed({"basic", "admin"})
    @Path("/{listId}")
    public Response modifyListItem(@PathParam("listId") Integer listId, ItemDetailsDto itemDetailsDto) {
        try {
            shoppingListService.modifyListItem(listId, itemDetailsDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }

    @DELETE
    @RolesAllowed({"basic", "admin"})
    @Path("/{listId}")
    public Response deleteListItem(@PathParam("listId") Integer listId, ItemDetailsDto itemDetailsDto) {
        try {
            shoppingListService.deleteListItem(listId, itemDetailsDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.serverError().entity(e.toString()).build();
        }
    }
}
