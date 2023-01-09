package himedia.campus.entity.campsite;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
