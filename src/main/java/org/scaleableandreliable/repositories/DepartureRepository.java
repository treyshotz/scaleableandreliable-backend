package org.scaleableandreliable.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.scaleableandreliable.models.Arrivals;
import org.scaleableandreliable.models.Departures;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DepartureRepository implements PanacheRepository<Departures> {
  
  public List<Departures> findAllByIcao24(String icao24) {
    var icao241 = find("icao24", icao24);
    return icao241.list();
  }
}
