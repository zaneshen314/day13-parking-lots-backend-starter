package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.Service.ParkingService;
import org.afs.pakinglot.domain.entity.Car;
import org.afs.pakinglot.domain.entity.bo.TicketBo;
import org.afs.pakinglot.domain.entity.vo.ParkingLotVo;
import org.afs.pakinglot.domain.entity.vo.TicketVo;
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

    @PostMapping("/park/{strategy}")
    public TicketVo park(@RequestBody Car car, @PathVariable String strategy) {
        return parkingService.park(car, strategy);
    }

    @PostMapping("/fetch")
    public TicketVo fetch(@RequestBody TicketBo ticketBo) {
        return parkingService.fetch(ticketBo);
    }

    @GetMapping
    public List<ParkingLotVo> getParkingLots() {
        return parkingService.getParkingLots();
    }
}