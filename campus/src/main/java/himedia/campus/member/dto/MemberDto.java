package himedia.campus.member.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {
	
	private String memberRole;
	
	@NotEmpty(message = "ID는 필수 입력 항목입니다.")
	private String memberId;
	
	@NotEmpty(message = "PassWord는 필수 입력 항목입니다.")
	@Length(min=5, max = 15, message = "5자 이상 15자 이하만 가능합니다.")
	private String memberPw;
	
	@NotEmpty(message = "이름은 필수 입력 항목입니다.")
	private String memberName;
	
	@NotEmpty(message = "전화번호는 필수 입력 항목입니다.")
	private String memberPhone;
	
	private Integer memberAge;
	
}
