package com.company.station.demo.repository;

import com.company.station.demo.model.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Shawn Li on 9/19/2018.
 */
public interface StationRepository extends CrudRepository<Station, Long>{
    Station findByStationId(String stationId);
    List<Station> findByName(String name);
    List<Station> findByHdEnabled(Boolean hdEnabled);
    @Transactional
    void deleteByStationId(String stationId);
}
