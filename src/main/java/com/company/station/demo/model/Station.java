package com.company.station.demo.model;

import javax.persistence.*;

import lombok.Data;
import java.io.Serializable;

/**
 * Created by CZWWBK on 9/18/2018.
 */
@Data
public class Station implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    private String stationId;
    private String name;
    private Boolean hdEnabled;
    private String callSign;
}
