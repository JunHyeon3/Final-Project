package himedia.campus.service.campsite;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.dto.campsite.CampsiteDto;
import himedia.campus.entity.campsite.Campsite;
import himedia.campus.entity.campsite.CampsiteImg;
import himedia.campus.entity.member.Member;
import himedia.campus.repository.campsite.CampsiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CampsiteService {

	private final CampsiteRepository campsiteRepository;
	private final CampsiteImgService campsiteImgService;

	public Long saveCampsite(CampsiteDto campsiteDto, Member member, List<MultipartFile> campsiteImgFiles)
			throws Exception {
		campsiteDto.setCampsiteManager(member.getMemberName());
		Campsite campsite = campsiteDto.toEntity();
		campsite.setMember(member);
		campsiteRepository.save(campsite);

		for (int i = 0; i < campsiteImgFiles.size(); i++) {
			CampsiteImg campsiteImg = new CampsiteImg();
			campsiteImg.setCampsite(campsite);
			campsiteImgService.saveCampsiteImg(campsiteImg, campsiteImgFiles.get(i));
			if (i == 0) {
				campsite.updateCampsiteMainImg(campsiteImg.getCampsiteImgPath());
			}
		}

		return campsite.getCampsiteId();
	}

	public Long updateCampsite(CampsiteDto campsiteDto, List<MultipartFile> campsiteImgFiles) throws Exception {
		Campsite findCampsite = campsiteRepository.findByCampsiteId(campsiteDto.getCampsiteId()).get();
		findCampsite.updateCampsite(campsiteDto);

		List<CampsiteImg> findCampsiteImg = campsiteImgService.findByCampsiteId(campsiteDto.getCampsiteId());
		for (int i = 0; i < campsiteImgFiles.size(); i++) {
			campsiteImgService.updateCampsiteImg(findCampsiteImg.get(i), campsiteImgFiles.get(i));
			if (i == 0) {
				findCampsite.updateCampsiteMainImg(findCampsiteImg.get(0).getCampsiteImgPath());
			}
		}

		return findCampsite.getCampsiteId();
	}

	public void deleteCampsite(Campsite campsite, List<CampsiteImg> findCampsiteImg) throws Exception {
		for (CampsiteImg fci : findCampsiteImg) {
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

	public Page<Campsite> findByThemeAndEnvironment(String searchTheme,
			String searchEnvironment,
			Pageable pageable) {
		return campsiteRepository.findByThemeAndEnvironment(searchTheme,
				searchEnvironment, 
				pageable);
	}

}
