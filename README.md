# stationDemo
Demo application for station service API

APIs following MVC pattern

Station.java
StationController.java
StationService.java
StationRepository.java

Contains 7 requests
GetMapping:
1. get all stations - retrieveStations("/stations")
2. get station by station Id - retrieveStationsById("/stations/id/{stationId}")
3. get stations by name - retrieveStationsByName("/stations/name/{name}")
4. get hd enabled stations - retrieveHdEnabledStations("/station")

PostMapping:
5. add station - createStation("/stations")

DeleteMapping:
6. delete station - deleteStation("/stations")

PutMapping
7. update station - updateStation("/stations/{id}")

Unit test covered 98% by Jacoco report, 2% on default DemoApplication.java

H2 integrated with entity class Station

testing flow using Postman
1. add station:

Post
localhost:8080/stations

{
	"stationId" : "1",
	"name" : "Sirius XM",
	"hdEnabled" : true,
	"callSign" : "callSign1"
}

2. add station:

Post
localhost:8080/stations

{
	"stationId" : "2",
	"name" : "NPR",
	"hdEnabled" : false,
	"callSign" : "callSign2"
}

3. get all stations:

Get
localhost:8080/stations

4. get station by stationId:

Get
http://localhost:8080/stations/id/1

5. get stations by name:

Get
http://localhost:8080/stations/name/NPR

6. get hd enabled stations:

Get
http://localhost:8080/station?hdEnabled=true

7. delete station:

Delete
localhost:8080/stations?stationId=1

8. update station:

Put
localhost:8080/stations/2

{
	"stationId" : "5",
	"name" : "ChillStation",
	"hdEnabled" : true,
	"callSign" : "callSign355"
}
