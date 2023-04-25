package org.scaleableandreliable.endpoints;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestPath;
import org.scaleableandreliable.models.Arrivals;
import org.scaleableandreliable.repositories.ArrivalRepository;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/arrival")
public class ArrivalResource {

  @Inject ArrivalRepository repo;

  @GET
  public String getArrivalById() {
    return repo.findById(-7708912395340845039L).getEstArrivalAirport();
  }

  @POST
  public String getArrivalById(@RestForm Long id) {
    return repo.findById(id).getEstArrivalAirport();
  }

  @POST
  @Path("icao24/{icao24}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Arrivals> getByIcao24(@RestPath String icao24) {
    var allByIcao24 = repo.findAllByIcao24(icao24);
    return allByIcao24;
  }

  @POST
  @Path("json")
  public String test(JsonObject obj) {
    return "hallo";
  }
}
