package himedia.campus.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;

import himedia.campus.entity.reservation.Reservation;
import himedia.campus.entity.reservation.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto {
	
	private Long reservationId;
	private LocalDateTime reservationDate;
	private Date checkinDate;
	private Date checkoutDate;
	private Integer headCount;
	private Integer totalPrice;
	private ReservationStatus reservationStatus;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Reservation toEntity() {
		return modelMapper.map(this, Reservation.class);
	}
	
}
