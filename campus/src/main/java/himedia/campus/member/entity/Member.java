package himedia.campus.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity @Setter @Getter
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberNo;
	
	private String memberRole;
	
	@NotEmpty(message = "필수 입력 항목입니다.")
	private String memberId;
	
	@NotEmpty(message = "필수 입력 항목입니다.")
	@Length(min=5, max = 15, message = "5자 이상 15자 이하만 가능합니다.")
	private String memberPw;
	
	@NotEmpty(message = "필수 입력 항목입니다.")
	private String memberName;
	
	@NotEmpty(message = "필수 입력 항목입니다.")
	private String memberPhone;
	
//	@NotEmpty(message = "필수 입력 항목입니다.") 
	private Integer memberAge;
	
}
