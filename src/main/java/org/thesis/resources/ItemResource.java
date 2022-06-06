package org.thesis.resources;

import io.quarkus.security.Authenticated;
import org.thesis.dtos.ItemDto;
import org.thesis.dtos.SimpleStringDto;
import org.thesis.dtos.UnitDto;
import org.thesis.exceptions.SimpleException;
import org.thesis.services.ItemService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Authenticated
@Path("/item")
public class ItemResource {

    @Inject
    ItemService itemService;

    @GET
    @RolesAllowed({"basic", "admin"})
    public Response getAllItem() {
        try {
            return Response.ok(itemService.getAllItem()).build();
        } catch (SimpleException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }

    @POST
    @RolesAllowed({"basic", "admin"})
    public Response addNewItem(ItemDto itemDto) {
        try {
            itemService.addNewItem(itemDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }

    @PUT
    @RolesAllowed({"basic", "admin"})
    public Response modifyItem(ItemDto itemDto) {
        try {
            itemService.modifyItem(itemDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }

    @DELETE
    @RolesAllowed("admin")
    public Response deleteItem(ItemDto itemDto) {
        try {
            itemService.deleteItem(itemDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }
}
