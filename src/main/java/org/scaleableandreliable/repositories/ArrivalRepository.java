package org.scaleableandreliable.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.scaleableandreliable.models.Arrivals;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ArrivalRepository implements PanacheRepository<Arrivals> {

  public List<Arrivals> findAllByIcao24(String icao24) {
    var icao241 = find("icao24", icao24);
    return icao241.list();
  }
}
