package org.thesis.resources;

import org.thesis.entities.Unit;
import org.thesis.services.UnitService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/unit")
public class UnitResource {

    @Inject
    UnitService unitService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unit> hello() {
        return unitService.getAllUnit();
    }
}