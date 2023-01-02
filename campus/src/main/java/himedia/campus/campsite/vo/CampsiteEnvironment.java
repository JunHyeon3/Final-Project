package himedia.campus.campsite.vo;

import lombok.Getter;

@Getter
public class CampsiteEnvironment {
	private String key;
	private String value;

	public CampsiteEnvironment(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
