package com.example.arst5backend.model.airlines;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightCapacity {
  @Id
  Long id;

  Timestamp departdatetime;
  Timestamp arrivedatetime;
  String departairport;
  String arriveairport;
  String flightnumber;
  String flighttype;
  int firstclassnum;
  int economyclassnum;
  Double price;
}
