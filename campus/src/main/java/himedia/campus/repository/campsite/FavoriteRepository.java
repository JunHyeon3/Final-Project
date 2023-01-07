package himedia.campus.repository.campsite;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.campus.entity.campsite.FavoriteCampsite;

public interface FavoriteRepository extends JpaRepository<FavoriteCampsite, Long> {
	Optional<FavoriteCampsite> findByMember_MemberNoAndCampsite_CampsiteId(Long memberNo, Long campsiteId);
}
