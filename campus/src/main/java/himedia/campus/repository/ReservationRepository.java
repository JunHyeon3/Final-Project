package himedia.campus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.entity.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Optional<Reservation> findByReservationId(Long reservationId);
}
