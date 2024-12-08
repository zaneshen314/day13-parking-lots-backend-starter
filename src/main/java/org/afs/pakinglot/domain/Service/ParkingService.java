package org.afs.pakinglot.domain.Service;

import org.afs.pakinglot.domain.constant.StrategyConstant;
import org.afs.pakinglot.domain.entity.Car;
import org.afs.pakinglot.domain.entity.ParkingLot;
import org.afs.pakinglot.domain.entity.Ticket;
import org.afs.pakinglot.domain.entity.vo.TicketVo;
import org.afs.pakinglot.domain.exception.NoAvailablePositionException;
import org.afs.pakinglot.domain.exception.UnrecognizedTicketException;
import org.afs.pakinglot.domain.repository.ParkingLotRepository;
import org.afs.pakinglot.domain.repository.TicketRepository;
import org.afs.pakinglot.domain.strategies.ParkingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ParkingService {
    private Map<String, ParkingStrategy> strategyMap;

    private ParkingLotRepository parkingLotRepository;

    private TicketRepository ticketRepository;

    public ParkingService(Map<String, ParkingStrategy> strategyMap,
                          ParkingLotRepository parkingLotRepository,
                          TicketRepository ticketRepository) {
        this.strategyMap = strategyMap;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
        if (ticketRepository.findAll().isEmpty()) {
            parkingLotRepository.save(new ParkingLot(null, "The Plaza Park", 9));
            parkingLotRepository.save(new ParkingLot(null, "City Mall Garage", 12));
            parkingLotRepository.save(new ParkingLot(null, "Office Tower Parking", 9));
        }
    }
    public List<String> getParkingStrategy() {
        return strategyMap.keySet().stream().toList();
    }

    @Transactional
    public TicketVo park(Car car, String strategy) {
        if (ticketRepository.findByPlateNumber(car.getPlateNumber()) != null) {
            throw new UnrecognizedTicketException();
        }
        ParkingStrategy parkingStrategy = strategyMap.getOrDefault(strategy, strategyMap.get(StrategyConstant.STANDARD_PARKING_BOY_STRATEGY));
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        ParkingLot parkingLot = parkingStrategy.findParkingLot(parkingLots);
        Integer slots = ticketRepository.countByParkingLotId(parkingLot.getId());
        if (slots == parkingLot.getCapacity()) {
            throw new NoAvailablePositionException();
        }
        Ticket ticket = new Ticket(car.getPlateNumber(), slots + 1, parkingLot);
        ticketRepository.save(ticket);
        parkingLotRepository.save(parkingLot);
        return ticketToVo(ticket);
    }


    private TicketVo ticketToVo(Ticket ticket) {
        TicketVo ticketVo = new TicketVo();
        ticketVo.setPlateNumber(ticket.getPlateNumber());
        ticketVo.setPosition(ticket.getPosition());
        ticketVo.setId(ticket.getId());
        ticketVo.setParkingLotId(ticket.getParkingLot().getId());
        return ticketVo;
    }
}