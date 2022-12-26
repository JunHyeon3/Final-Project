package himedia.campus.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
