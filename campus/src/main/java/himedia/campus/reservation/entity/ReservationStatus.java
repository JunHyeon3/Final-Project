package himedia.campus.reservation.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
	WAITING("대기 중"), CONFIRM("예약 완료");

	private String value;
	
	private ReservationStatus(String value) {
		this.value = value;
	}
	
}
