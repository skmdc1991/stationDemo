package com.company.station.demo.service;

import com.company.station.demo.model.Station;
import com.company.station.demo.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Shawn Li on 9/19/2018.
 */
@Service
public class StationService {
    @Autowired
    StationRepository stationRepository;

    public List<Station> getAll(){
        return (List<Station>) stationRepository.findAll();
    }

    public Station getById(String stationId){
        return stationRepository.findByStationId(stationId);
    }

    public List<Station> getByName(String name){
        return stationRepository.findByName(name);
    }

    public List<Station> getByHdStatus(Boolean hdEnabled){
        return stationRepository.findByHdEnabled(hdEnabled);
    }

    public Station add(Station station){
        if(station != null) {
            Station existStations = stationRepository.findByStationId(station.getStationId());
            if (existStations == null) {
                return stationRepository.save(station);
            }
        }
        return null;
    }

    public void delete(String stationId){
        stationRepository.deleteByStationId(stationId);
    }

    public Station update(String id, Station station){
        Station updateStation = stationRepository.findByStationId(id);
        if(updateStation != null){
            station.setStationId(id);
            return stationRepository.save(station);
        }
        return null;
    }

}
