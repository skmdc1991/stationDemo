package com.company.station.demo.controller;

import com.company.station.demo.model.Station;
import com.company.station.demo.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by CZWWBK on 9/19/2018.
 */
@RestController
public class StationController {

    @Autowired
    StationService stationService;

    @PostMapping("/stations")
    public ResponseEntity<Object> createStation(@RequestBody Station station){
        Station savedStation = stationService.add(station);
        if(savedStation == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(savedStation.getStationId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/stations")
    public void deleteStation(@RequestParam String stationId){
        stationService.delete(stationId);
    }

    @PutMapping("/stations/{stationId}")
    public ResponseEntity<Void> updateStation(@PathVariable String stationId, @RequestBody Station station){
        Station updatedStation = stationService.update(stationId, station);
        if(updatedStation == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stations")
    public ResponseEntity<List<Station>> retrieveStations(){
        return new ResponseEntity(stationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/stations/id/{stationId}")
    public ResponseEntity<Station> retrieveStationsById(@PathVariable("stationId") String stationId){
        return new ResponseEntity(stationService.getById(stationId), HttpStatus.OK);
    }

    @GetMapping("/stations/name/{name}")
    public ResponseEntity<List<Station>> retrieveStationsByName(@PathVariable("name") String name){
        return new ResponseEntity(stationService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/station")
    public ResponseEntity<List<Station>> retrieveHdEnabledStations(@RequestParam Boolean hdEnabled){
        return new ResponseEntity(stationService.getByHdStatus(hdEnabled), HttpStatus.OK);
    }

}