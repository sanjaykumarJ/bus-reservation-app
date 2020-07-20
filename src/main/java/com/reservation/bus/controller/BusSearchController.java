package com.reservation.bus.controller;

import com.reservation.bus.model.Bus;
import com.reservation.bus.service.BusSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BusSearchController {
    @Autowired
    private BusSearchService busSearchService;

    List<Bus> getAllBuses() {
        return busSearchService.findAll(Sort.by(Sort.Order.desc("price")));
    }

    @RequestMapping(value = "/querybuses", method = RequestMethod.GET)
    public List<Bus> query(@RequestParam("departureCity") String departureCity, @RequestParam("arrivalCity") String arrivalCity) throws Exception {
        if (departureCity != null || arrivalCity != null) {
            return getAllBuses().stream().filter(bus ->
                    bus.getDepartureLoc().equalsIgnoreCase(departureCity) && bus.getArrivalLoc().equalsIgnoreCase(arrivalCity)
            ).collect(Collectors.toList());
        }

        return new ArrayList<Bus>();
    }
}