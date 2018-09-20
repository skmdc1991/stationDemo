package com.company.station.demo.model;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by CZWWBK on 9/18/2018.
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Station implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String stationId;
    private String name;
    private Boolean hdEnabled;
    private String callSign;

    public Station(String stationId, String name, Boolean hdEnabled, String callSign){
        this.stationId = stationId;
        this.name = name;
        this.hdEnabled = hdEnabled;
        this.callSign = callSign;
    }
}
