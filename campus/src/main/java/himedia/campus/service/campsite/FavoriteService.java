package himedia.campus.service.campsite;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.campsite.FavoriteCampsite;
import himedia.campus.entity.member.Member;
import himedia.campus.repository.MemberRepository;
import himedia.campus.repository.campsite.CampsiteRepository;
import himedia.campus.repository.campsite.FavoriteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {

	private final FavoriteRepository favoritRepository;
	private final MemberRepository memberRepository;
	private final CampsiteRepository campsiteRepository;
	
	public FavoriteCampsite addFavoriteCampsite(String memberId, Long campsiteId) {
		Member member = memberRepository.findByMemberId(memberId).get();
		Campsite campsite = campsiteRepository.findByCampsiteId(campsiteId).get();
		
		FavoriteCampsite favoriteCampsite = new FavoriteCampsite();
		favoriteCampsite.setMember(member);
		favoriteCampsite.setCampsite(campsite);
		
		return favoritRepository.save(favoriteCampsite);
	}

	public void deleteFavoriteCampsite(String memberId, Long campsiteId) {
		Long memberNo = memberRepository.findByMemberId(memberId).get().getMemberNo();
		
		FavoriteCampsite favoriteCampsite = findByMemberNoAndCampsiteId(memberNo, campsiteId).get();
		favoritRepository.delete(favoriteCampsite);
	}

	public Optional<FavoriteCampsite> findByMemberNoAndCampsiteId(Long memberNo, Long campsiteId) {
		return favoritRepository.findByMember_MemberNoAndCampsite_CampsiteId(memberNo, campsiteId);
	}

}
