package himedia.campus.vo;

import lombok.Getter;

@Getter
public class CampsiteTheme {
	private String key;
	private String value;

	public CampsiteTheme(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
