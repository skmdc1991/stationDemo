package com.company.station.demo.controller;

import com.company.station.demo.model.Station;
import com.company.station.demo.repository.StationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by CZWWBK on 9/20/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StationControllerTest {
    @Autowired
    StationController stationController;
    @Autowired
    StationRepository stationRepository;

    Station stationData1 = new Station("1", "Sirius XM", true, "callsSign1");
    Station stationData2 = new Station("2", "NPR", false, "callsSign2");
    Station stationData3 = new Station("3", "NPR1", false, "callsSign22");

    @Test
    public void createStation(){
        stationRepository.deleteAll();
        //check if null station input will generate no content http status 204
        ResponseEntity emptyStation = stationController.createStation(null);
        Assert.assertEquals("204", emptyStation.getStatusCode().toString());

        Station station = new Station();
        station.setStationId("1");
        station.setName("Sirius XM");
        station.setHdEnabled(true);
        station.setCallSign("callsSign1");
        //check successfully created station is same as input station
        Station createdStation = (Station)stationController.createStation(station).getBody();
        Assert.assertEquals("1", createdStation.getStationId());
        Assert.assertEquals("Sirius XM", createdStation.getName());
        Assert.assertTrue(createdStation.getHdEnabled());
        Assert.assertEquals("callsSign1", createdStation.getCallSign());
    }

    @Test
    public void deleteStation(){
        stationRepository.deleteAll();
        stationController.createStation(stationData1);
        stationController.createStation(stationData2);

        stationController.deleteStation("1");
        Assert.assertEquals(1, stationController.retrieveStations().getBody().size());
        Assert.assertEquals("NPR", stationController.retrieveStations().getBody().get(0).getName());
    }

    @Test
    public void retrieveAllStations(){
        stationRepository.deleteAll();
        stationController.createStation(stationData1);
        stationController.createStation(stationData2);

        //check size of stations
        List<Station> allStations = stationController.retrieveStations().getBody();
        Assert.assertEquals(2, allStations.size());
    }

    @Test
    public void retrieveStationById(){
        stationRepository.deleteAll();
        stationController.createStation(stationData1);
        stationController.createStation(stationData2);

        //check search by Station Id
        Station station = stationController.retrieveStationsById("1").getBody();
        Assert.assertEquals("1", station.getStationId());
        Assert.assertEquals("Sirius XM", station.getName());
        Assert.assertTrue(station.getHdEnabled());
        Assert.assertEquals("callsSign1", station.getCallSign());
    }

    @Test
    public void retrieveStationsByName(){
        stationRepository.deleteAll();
        stationController.createStation(stationData1);
        stationController.createStation(stationData2);

        //check search by name
        List<Station> stations = stationController.retrieveStationsByName("Sirius XM").getBody();

        Assert.assertEquals("1", stations.get(0).getStationId());
        Assert.assertEquals("Sirius XM", stations.get(0).getName());
        Assert.assertTrue(stations.get(0).getHdEnabled());
        Assert.assertEquals("callsSign1", stations.get(0).getCallSign());
    }

    @Test
    public void retrieveAllHdEnabledStations(){
        stationRepository.deleteAll();
        stationController.createStation(stationData1);
        stationController.createStation(stationData2);
        stationController.createStation(stationData3);

        //check search by hdEnabled
        List<Station> hdStations = stationController.retrieveHdEnabledStations(false).getBody();
        Assert.assertEquals(2, hdStations.size());
        Assert.assertFalse(hdStations.get(0).getHdEnabled());
    }

    @Test
    public void updateStation(){
        stationRepository.deleteAll();
        //check not found station return 404
        Assert.assertEquals("404", stationController.updateStation("2", stationData1).getStatusCode().toString());
        stationController.createStation(stationData1);

        //check found station return empty content build
        Assert.assertEquals("204", stationController.updateStation("1", stationData3).getStatusCode().toString());
    }
}
