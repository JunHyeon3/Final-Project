package himedia.campus.service.campsite;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.campsite.FavoriteCampsite;
import himedia.campus.entity.member.Member;
import himedia.campus.repository.campsite.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {

	private final FavoriteRepository favoritRepository;
	
	public void addFavoriteCampsite(Member member, Campsite campsite) {
		FavoriteCampsite favoriteCampsite = new FavoriteCampsite();
		favoriteCampsite.setMember(member);
		favoriteCampsite.setCampsite(campsite);
		favoritRepository.save(favoriteCampsite);
	}

	public void deleteFavoriteCampsite(Long memberNo, Long campsiteId) {
		FavoriteCampsite favoriteCampsite = findByMemberNoAndCampsiteId(memberNo, campsiteId).get();
		favoritRepository.delete(favoriteCampsite);
	}

	public Optional<FavoriteCampsite> findByMemberNoAndCampsiteId(Long memberNo, Long campsiteId) {
		return favoritRepository.findByMember_MemberNoAndCampsite_CampsiteId(memberNo, campsiteId);
	}

}
