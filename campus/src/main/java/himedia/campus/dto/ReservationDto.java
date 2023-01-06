package himedia.campus.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
	@NotBlank(message="입실일을 선택해 주세요.")
	private String checkinDate;
	@NotBlank(message="퇴실일을 선택해 주세요.")
	private String checkoutDate;
	@NotNull(message="이용 인원을 입력해 주세요.")
	private Integer headCount;
	@Positive(message = "예약일을 확인해 주세요.")
	private Integer totalPrice;
	private ReservationStatus reservationStatus;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Reservation toEntity() {
		return modelMapper.map(this, Reservation.class);
	}
	
}
