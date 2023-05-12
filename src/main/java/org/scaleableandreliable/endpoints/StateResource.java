package org.scaleableandreliable.endpoints;

import io.agroal.api.AgroalDataSource;
import org.jboss.resteasy.reactive.RestPath;
import org.scaleableandreliable.models.AircraftState;
import org.scaleableandreliable.repositories.StateRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/state")
public class StateResource {



  @Inject StateRepository repo;


  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello from RESTEasy Reactive";
  }

  @POST
  @Path("icao24/{icao24}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<AircraftState> getByIcao24(@RestPath String icao24) {
    var allByIcao24 = repo.findAllByIcao24(icao24);
    return allByIcao24;
  }

  @GET
  @Path("active")
  @Produces(MediaType.APPLICATION_JSON)
  public List<AircraftState> getAllState() {
    return repo.findLatest();
  }
}
