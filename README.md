# Team 5 Project Backend
This is the backend repo for the airline reservation system of Team 5, it uses JAVA spring boot framework and connects to a MySQL database and a REACT frontend.

It currently provides a simple search api `/search` which takes a `SearchRequest` of:
* `departureAirport`
* `arrivalAirport`
* `departureDate`

and returns the flights that meet the criteria.

## How to start a local backend server
1. Start a local MySQL instance and create a schema in MySQL Workbench
2. Run `flightdata_deltas-1.sql` (provided by the project) to create the tables
3. Update `application.properties` with the corresponding MySQL `url`, `username`, and `password`
4. Run the backend application

We can test the local backend server by sending `GET` request `http://localhost:8080/search` in Postman with the following `Body`:
```
{
    "departureAirport": "Atlanta (ATL)",
    "arrivalAirport": "Tucson (TUS)",
    "departureDate": "2023-01-02"
}
```

An example output is shown as follows:
```
{
    "departureFlights": [
        {
            "departdatetime": "2023-01-02T22:37:00.000+00:00",
            "arrivedatetime": "2023-01-03T02:39:00.000+00:00",
            "departairport": "Atlanta (ATL)",
            "arriveairport": "Tucson (TUS)",
            "flightnumber": "DL1600"
        },
        {
            "departdatetime": "2023-01-02T04:26:00.000+00:00",
            "arrivedatetime": "2023-01-01T08:30:00.000+00:00",
            "departairport": "Atlanta (ATL)",
            "arriveairport": "Tucson (TUS)",
            "flightnumber": "DL2317"
        },
        {
            "departdatetime": "2023-01-02T20:00:00.000+00:00",
            "arrivedatetime": "2023-01-03T00:07:00.000+00:00",
            "departairport": "Atlanta (ATL)",
            "arriveairport": "Tucson (TUS)",
            "flightnumber": "DL748"
        }
    ],
    "returnFlights": null,
    "success": true
}
```
