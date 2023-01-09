package himedia.campus.entity.reservation;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationId;
	private LocalDateTime reservationDate;
	private Date checkinDate;
	private Date checkoutDate;
	private Integer headCount;
	private Integer totalPrice;
	@Enumerated(value=EnumType.STRING)
	private ReservationStatus reservationStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campsite_id")
	private Campsite campsite;

	public void updateReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
		
}
