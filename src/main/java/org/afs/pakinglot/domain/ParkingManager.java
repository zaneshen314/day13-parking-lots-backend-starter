package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;

import java.time.LocalDateTime;
import java.util.Arrays;

public class ParkingManager {

    private final ParkingBoy standardParkingBoy;
    private final ParkingBoy smartParkingBoy;
    private final ParkingBoy superParkingBoy;

    public ParkingManager() {
        ParkingLot plazaPark = new ParkingLot(1, "The Plaza Park", 9);
        ParkingLot cityMallCarage = new ParkingLot(2, "City Mall Carage", 12);
        ParkingLot officeTowerParking = new ParkingLot(3, "Office Tower Parking", 9);
        standardParkingBoy = new ParkingBoy(Arrays.asList(plazaPark, cityMallCarage, officeTowerParking), new SequentiallyStrategy());
        smartParkingBoy = new ParkingBoy(Arrays.asList(plazaPark, cityMallCarage, officeTowerParking), new MaxAvailableStrategy());
        superParkingBoy = new ParkingBoy(Arrays.asList(plazaPark, cityMallCarage, officeTowerParking), new AvailableRateStrategy());
    }

    public Ticket park(String strategy, String plateNumber) {
        Car car = new Car(plateNumber);
        ParkingBoy selectedParkingBoy = selectParkingBoy(strategy);
        Ticket ticket = selectedParkingBoy.park(car);
        return new Ticket(ticket.plateNumber(), ticket.position(), ticket.parkingLot(), LocalDateTime.now());
    }

    private ParkingBoy selectParkingBoy(String strategy) {
        return switch (strategy.toUpperCase()) {
            case "SMART" -> smartParkingBoy;
            case "SUPER" -> superParkingBoy;
            default -> standardParkingBoy;
        };
    }
}