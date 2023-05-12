package org.scaleableandreliable.repositories;

import io.agroal.api.AgroalDataSource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import org.scaleableandreliable.models.AircraftState;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StateRepository implements PanacheRepository<AircraftState> {

  private AgroalDataSource ds;

  public AgroalDataSource getDs() {
    return ds;
  }

  @Inject
  public StateRepository setDs(AgroalDataSource ds) {
    this.ds = ds;
    return this;
  }

  AircraftState findById(long id) {
    return findById(id);
  }

  public List<AircraftState> findAllByIcao24(String icao24) {
    var icao241 = find("icao24", icao24);
    return icao241.list();
  }

  public List<AircraftState> findAllInTheAir() {
    var sort = Sort.by("timeStamp").descending();
    var timeStamp = find("callsign", "ELY322");

    return list("onGround", false);
  }

  public ArrayList<AircraftState> findLatest() {

    try (var session = ds.getConnection();
        var statement = session.createStatement()) {
      String maxSql =
          "select s.timeStamp from AircraftState s order by s.timestamp desc OFFSET 0 ROWS FETCH FIRST 5 ROWS ONLY";
      statement.execute(maxSql);

      var resultSet = statement.getResultSet();
      resultSet.next();
      var max = resultSet.getLong("timestamp");

      String latest = "select * from [dbo].[AircraftState] where timestamp = " + max;
      statement.execute(latest);

      resultSet = statement.getResultSet();
      var states = new ArrayList<AircraftState>();
      while (resultSet.next()) {
        states.add(
            new AircraftState(
                resultSet.getString("icao24"),
                resultSet.getString("callsign"),
                resultSet.getString("originCountry"),
                resultSet.getInt("timePosition"),
                resultSet.getInt("lastContact"),
                resultSet.getFloat("longitude"),
                resultSet.getFloat("latitude"),
                resultSet.getFloat("baroAltitude"),
                resultSet.getBoolean("onGround"),
                resultSet.getFloat("velocity"),
                resultSet.getFloat("trueTrack"),
                resultSet.getFloat("verticalRate"),
                resultSet.getFloat("geoAltitude"),
                resultSet.getString("squawk"),
                resultSet.getBoolean("spi"),
                resultSet.getInt("positionSource"),
                resultSet.getInt("category"),
                resultSet.getLong("timeStamp")));
      }
      
      return states;
    } catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }
}
