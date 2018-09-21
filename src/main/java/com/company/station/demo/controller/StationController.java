package com.company.station.demo.controller;

import com.company.station.demo.model.Station;
import com.company.station.demo.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;

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
        //return 204 if not saved successfully
        if(savedStation == null){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity(savedStation, HttpStatus.CREATED);
    }

    @DeleteMapping("/stations")
    public void deleteStation(@RequestParam String stationId){
        stationService.delete(stationId);
    }

    @PutMapping("/stations/{id}")
    public ResponseEntity<Object> updateStation(@PathVariable("id") String id, @RequestBody Station station){
        Station updatedStation = stationService.update(id, station);
        //return not 404 if stationId not found
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
