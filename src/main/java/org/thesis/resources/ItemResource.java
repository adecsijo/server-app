package org.thesis.resources;

import io.quarkus.security.Authenticated;
import org.thesis.dtos.ItemDto;
import org.thesis.exceptions.SimpleException;
import org.thesis.services.ItemService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Authenticated
@Path("/item")
public class ItemResource {

    @Inject
    ItemService itemService;

    @GET
    @RolesAllowed({"basic", "admin"})
    @Path("/{listId}")
    public Response getAllItem(@PathParam("listId") Integer listId) {
        try {
            return Response.ok(itemService.getAllItemByListId(listId)).build();
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
    @Path("/{listId}")
    public Response addNewItem(@PathParam("listId") Integer listId, ItemDto itemDto) {
        try {
            itemService.addNewItem(listId, itemDto);
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
    public Response deleteItem(ItemDto itemDto) {
        try {
            itemService.deleteItem(itemDto);
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
