package org.afs.pakinglot.domain.repository;

import org.afs.pakinglot.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
