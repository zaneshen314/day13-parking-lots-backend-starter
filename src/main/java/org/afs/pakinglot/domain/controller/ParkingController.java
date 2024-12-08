package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.Service.ParkingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/parking")
public class ParkingController {
    private ParkingService parkingService;
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
    @GetMapping("/strategy")
    public List<String> getParkingStrategy() {
        return parkingService.getParkingStrategy();
    }
}