package com.company.station.demo.service;

import com.company.station.demo.model.Station;
import com.company.station.demo.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CZWWBK on 9/19/2018.
 */
@Service
public class StationService {
    @Autowired
    StationRepository stationRepository;

    public List<Station> getAll(){
        return (List<Station>)stationRepository.findAll();
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
        Station existStation = stationRepository.findByStationId(station.getStationId());
        //station already exist, return null
        if(existStation != null){
            return null;
        }
        return stationRepository.save(station);
    }

    public void delete(String stationId){
        stationRepository.deleteByStationId(stationId);
    }

    public Station update(String stationId, Station station){
        Station addedStation = stationRepository.findByStationId(stationId);
        if(addedStation != null){
            station.setStationId(stationId);
            stationRepository.save(station);
        }
        return addedStation;
    }

}
