package org.afs.pakinglot.domain.Service;

import org.afs.pakinglot.domain.strategies.ParkingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkingService {
    private Map<String, ParkingStrategy> strategyMap;
    public ParkingService(Map<String, ParkingStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }
    public List<String> getParkingStrategy() {
        return strategyMap.keySet().stream().toList();
    }
}