package org.thesis.resources;

import io.quarkus.security.Authenticated;
import org.thesis.dtos.SimpleStringDto;
import org.thesis.dtos.UnitDto;
import org.thesis.exceptions.SimpleException;
import org.thesis.services.UnitService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/unit")
@Authenticated
public class UnitResource {

    @Inject
    UnitService unitService;

    @GET
    @RolesAllowed({"basic", "admin"})
    public Response getAllUnit() {
        try {
            return Response.ok(unitService.getAllUnit()).build();
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
    public Response addNewUnit(UnitDto unitDto) {
        try {
            unitService.addNewUnit(unitDto);
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
    public Response modifyUnit(UnitDto unitDto) {
        try {
            unitService.modifyUnit(unitDto);
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
    @RolesAllowed("admin")
    public Response deleteUnit(UnitDto unitDto) {
        try {
            unitService.deleteUnit(unitDto);
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