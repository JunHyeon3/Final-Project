package himedia.campus.campsite.vo;

import lombok.Getter;

@Getter
public class CampsiteFacilitie {
	private String key;
	private String value;

	public CampsiteFacilitie(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
