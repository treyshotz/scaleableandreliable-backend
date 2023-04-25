package org.scaleableandreliable.endpoints;

import org.jboss.resteasy.reactive.RestPath;
import org.scaleableandreliable.models.Arrivals;
import org.scaleableandreliable.models.Departures;
import org.scaleableandreliable.repositories.DepartureRepository;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("departure/")
public class DepartureResource {
  
  @Inject
  DepartureRepository repo;
  
  @POST
  @Path("icao24/{icao24}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Departures> getByIcao24(@RestPath String icao24) {
    return repo.findAllByIcao24(icao24);
  }
  
}
