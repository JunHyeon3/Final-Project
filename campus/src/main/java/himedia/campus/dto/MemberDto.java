package himedia.campus.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import himedia.campus.entity.member.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
	
	@NotNull(message = "사용자/관리자 중 하나를 선택해주세요.")
	private MemberRole memberRole;
	
	@NotEmpty(message = "ID는 필수 입력 항목입니다.")
	private String memberId;
	
	@NotEmpty(message = "PassWord는 필수 입력 항목입니다.")
	@Length(min=5, max = 15, message = "5자 이상 15자 이하만 가능합니다.")
	private String memberPw;
	
	@NotEmpty(message = "이름은 필수 입력 항목입니다.")
	private String memberName;
	
	@NotEmpty(message = "전화번호는 필수 입력 항목입니다.")
	private String memberPhone;

	@NotNull(message="나이는 필수 입력 항목입니다.")
	private Integer memberAge;
	
}
