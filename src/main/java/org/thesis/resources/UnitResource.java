package org.thesis.resources;

import io.quarkus.security.Authenticated;
import org.thesis.dtos.SimpleStringDto;
import org.thesis.dtos.UnitDto;
import org.thesis.exceptions.SimpleException;
import org.thesis.services.UnitService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Arrays;

@Path("/unit")
@Authenticated
public class UnitResource {

    @Inject
    UnitService unitService;

    @GET
    @Path("/getAllUnit")
    @RolesAllowed({"basic", "admin"})
    public Response getAllUnit() {
        try {
            return Response.ok(unitService.getAllUnit()).build();
        } catch (SimpleException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }

    @POST
    @Path("/addNewUnit")
    @RolesAllowed({"basic", "admin"})
    public Response addNewUnit(UnitDto unitDto) {
        try {
            unitService.addNewUnit(unitDto);
            return Response.noContent().build();
        } catch (SimpleException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new SimpleStringDto(e.getMessage())).build();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new SimpleStringDto(e.toString())).build();
        }
    }
}