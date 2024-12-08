package org.afs.pakinglot.domain.Controller;

import org.afs.pakinglot.domain.constant.StrategyConstant;
import org.afs.pakinglot.domain.entity.ParkingLot;
import org.afs.pakinglot.domain.repository.ParkingLotRepository;
import org.afs.pakinglot.domain.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ParkingControllerTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @BeforeEach
    void setUp() {
        givenDataParkingLotJpaRepository();
    }

    private void givenDataParkingLotJpaRepository() {
        ticketRepository.deleteAll();
        parkingLotRepository.deleteAll();
        parkingLotRepository.save(new ParkingLot(null, "The Plaza Park", 9));
        parkingLotRepository.save(new ParkingLot(null, "City Mall Garage", 12));
        parkingLotRepository.save(new ParkingLot(null, "Office Tower Parking", 9));
    }

    @Test
    void should_return_strategies_when_get_strategies() throws Exception {
        //given
        //when
        //then
        client.perform(MockMvcRequestBuilders.get("/parking/strategy"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(StrategyConstant.SUPER_SMART_PARKING_BOY_STRATEGY))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1]").value(StrategyConstant.STANDARD_PARKING_BOY_STRATEGY))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2]").value(StrategyConstant.SMART_PARKING_BOY_STRATEGY));
    }
}
