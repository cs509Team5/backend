package com.example.arst5backend.controller;

import com.example.arst5backend.service.view.IViewService;
import dto.ViewRequest;
import dto.ViewResponse;
import dto.FlightInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ViewDetailTest {

  @InjectMocks
  private ViewDetail viewDetail;

  @Mock
  private IViewService viewService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testView() {

    FlightInfo flightInfo = new FlightInfo();
    flightInfo.setDepartAirport("JFK");
    flightInfo.setDepartDatetime(new Timestamp(System.currentTimeMillis()));
    flightInfo.setFlightNumber("DL1234");
    when(viewService.viewDetail(any(), any(), any())).thenReturn(flightInfo);

    ViewRequest viewRequest = new ViewRequest();
    viewRequest.setDepartureAirport("JFK");
    viewRequest.setDepartureDate(new Date(System.currentTimeMillis()));
    viewRequest.setFlightNumber("DL1234");

    ViewResponse viewResponse = viewDetail.view(viewRequest);

    assertEquals(flightInfo, viewResponse.getFlightDetail());
  }
}
