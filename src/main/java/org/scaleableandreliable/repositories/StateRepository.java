package org.scaleableandreliable.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.scaleableandreliable.models.AircraftState;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class StateRepository implements PanacheRepository<AircraftState> {

  AircraftState findById(long id) {
    return findById(id);
  }

  public List<AircraftState> findAllByIcao24(String icao24) {
    var icao241 = find("icao24", icao24);
    return icao241.list();
  }
}
