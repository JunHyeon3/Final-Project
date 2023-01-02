package himedia.campus.campsite.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import himedia.campus.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FavoriteCampsite {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long favoriteId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_no")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campsite_id")
	private Campsite campsite;
	
    public static FavoriteCampsite createFavoriteCampsite(Member member) {
    	FavoriteCampsite favoriteCampsite = new FavoriteCampsite();
    	favoriteCampsite.setMember(member);
        return favoriteCampsite;
    }
	
}
