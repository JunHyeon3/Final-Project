package himedia.campus.member.entity;

import lombok.Getter;

@Getter
public enum MemberRole {
	
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	private String value;
	
	private MemberRole(String value) {
		this.value = value;
	}
	
}
