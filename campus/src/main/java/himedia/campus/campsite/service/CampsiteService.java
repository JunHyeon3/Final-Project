package himedia.campus.campsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.campsite.dto.CampsiteDto;
import himedia.campus.campsite.entity.Campsite;
import himedia.campus.campsite.entity.CampsiteImg;
import himedia.campus.campsite.repository.CampsiteRepository;
import himedia.campus.member.entity.Member;
import himedia.campus.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CampsiteService {

	private final CampsiteRepository campsiteRepository;
	private final CampsiteImgService campsiteImgService;
	private final MemberRepository memberRepository;
	
	public Long saveCampsite(CampsiteDto campsiteDto, String memberId,
							List<String> campsiteEnvironment,
							List<String> campsiteFacilitie, 
							List<String> campsiteTheme,
							List<MultipartFile> campsiteImgFiles) throws Exception {
		Member findMember = memberRepository.findByMemberId(memberId).get();
		campsiteDto.setCampsiteManager(findMember.getMemberName());
		campsiteDto.setCampsiteEnvironment(String.join(", ", campsiteEnvironment));
		campsiteDto.setCampsiteFacilitie(String.join(", ", campsiteFacilitie));
		campsiteDto.setCampsiteTheme(String.join(", ", campsiteTheme));
		
		Campsite campsite = campsiteDto.toEntity();
		campsite.setMember(findMember);
		campsiteRepository.save(campsite);
		
		for(int i=0; i<campsiteImgFiles.size(); i++) {
			CampsiteImg campsiteImg = new CampsiteImg();
			campsiteImg.setCampsite(campsite);
			campsiteImgService.saveCampsiteImg(campsiteImg, campsiteImgFiles.get(i));
			if(i==0) {
				campsite.updateCampsiteMainImg(campsiteImg.getCampsiteImgPath());
			}
		}
		
		return campsite.getCampsiteId(); 
	}
	
	public Long updateCampsite(CampsiteDto campsiteDto, 
								List<String> campsiteEnvironment,
								List<String> campsiteFacilitie, 
								List<String> campsiteTheme,
								List<MultipartFile> campsiteImgFiles) throws Exception {
		campsiteDto.setCampsiteEnvironment(String.join(", ", campsiteEnvironment));
		campsiteDto.setCampsiteFacilitie(String.join(", ", campsiteFacilitie));
		campsiteDto.setCampsiteTheme(String.join(", ", campsiteTheme));
		
		Campsite findCampsite = campsiteRepository.findByCampsiteId(campsiteDto.getCampsiteId()).get();
		findCampsite.updateCampsite(campsiteDto);
		
		List<CampsiteImg> findCampsiteImg = campsiteImgService.findByCampsiteId(campsiteDto.getCampsiteId());
		for(int i=0; i<campsiteImgFiles.size(); i++) {
			campsiteImgService.updateCampsiteImg(findCampsiteImg.get(i), campsiteImgFiles.get(i));
			if(i==0) {
				findCampsite.updateCampsiteMainImg(findCampsiteImg.get(0).getCampsiteImgPath());
			}
		}
		
		return findCampsite.getCampsiteId(); 
	}
	
	public void deleteCampsite(Campsite campsite, List<CampsiteImg> findCampsiteImg) throws Exception {
		for(CampsiteImg fci: findCampsiteImg) {
			campsiteImgService.deleteCampsiteImg(fci);
		}
		campsiteRepository.delete(campsite);
	}
	
	public Optional<Campsite> findByCampsiteId(Long campsiteId) {
		return campsiteRepository.findByCampsiteId(campsiteId);
	}
	
	public Page<Campsite> pageList(Pageable pageable) {
		return campsiteRepository.findAll(pageable);
	}

	public Page<Campsite> findByEnviornmentAndTheme(String campsiteEnvironment, String campsiteTheme, Pageable pageable) {
		return campsiteRepository.findByCampsiteEnvironmentContainingAndCampsiteThemeContaining(campsiteEnvironment, campsiteTheme, pageable);
	}
	
}
