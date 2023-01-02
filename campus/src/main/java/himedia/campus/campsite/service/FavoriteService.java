package himedia.campus.campsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.campus.campsite.entity.Campsite;
import himedia.campus.campsite.entity.FavoriteCampsite;
import himedia.campus.campsite.repository.FavoriteRepository;
import himedia.campus.member.entity.Member;
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
		
//		FavoriteCampsite favoriteCampsite = findByMemberNo(member.getMemberNo());
//		if(favoriteCampsite == null) {
//			favoriteCampsite = FavoriteCampsite.createFavoriteCampsite(member);
//			favoritRepository.save(favoriteCampsite);
//		}
//		
//		favoriteCampsite.setCampsite(campsite);
	}

	public void deleteFavoriteCampsite(Long memberNo, Long campsiteId) {
		FavoriteCampsite favoriteCampsite = findByMemberNoAndCampsiteId(memberNo, campsiteId).get();
		favoritRepository.delete(favoriteCampsite);
	}

	public Optional<FavoriteCampsite> findByMemberNoAndCampsiteId(Long memberNo, Long campsiteId) {
		return favoritRepository.findByMember_MemberNoAndCampsite_CampsiteId(memberNo, campsiteId);
	}

	//	public List<FavoriteCampsite> findByMemberNo(Long memberNo) {
//		return favoritRepository.findByMember_MemberNo(memberNo);
//	}

}
