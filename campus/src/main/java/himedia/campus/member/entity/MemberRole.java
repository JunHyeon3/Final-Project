package himedia.campus.member.entity;

import lombok.Getter;

@Getter
public enum MemberRole {
	USER("ROLE_USER", "사용자"), ADMIN("ROLE_ADMIN", "관리자");
	
	private String value;
	private String text;
	
	private MemberRole(String value, String text) {
		this.value = value;
		this.text = text;
	}
	
}
