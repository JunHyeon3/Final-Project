package himedia.campus.campsite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.campus.campsite.entity.FavoriteCampsite;

public interface FavoriteRepository extends JpaRepository<FavoriteCampsite, Long> {
//	List<FavoriteCampsite> findByMember_MemberNo(Long memberNo);
	Optional<FavoriteCampsite> findByMember_MemberNoAndCampsite_CampsiteId(Long memberNo, Long campsiteId);
//	FavoriteCampsite findbyCampsite_CampsiteId(Long campsiteId);
}
