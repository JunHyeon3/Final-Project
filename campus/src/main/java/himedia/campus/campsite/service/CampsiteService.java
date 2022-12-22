package himedia.campus.campsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.campsite.dto.CampsiteDto;
import himedia.campus.campsite.entity.Campsite;
import himedia.campus.campsite.entity.CampsiteImg;
import himedia.campus.campsite.repository.CampsiteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CampsiteService {

	private final CampsiteRepository campsiteRepository;
	private final CampsiteImgService campsiteImgService;
	
	public Long saveCampsite(CampsiteDto campsiteDto, 
							List<String> campsiteEnvironment,
							List<String> campsiteFacilitie, 
							List<String> campsiteTheme,
							List<MultipartFile> campsiteImgFiles) throws Exception {
		campsiteDto.setCampsiteEnvironment(String.join(", ", campsiteEnvironment));
		campsiteDto.setCampsiteFacilitie(String.join(", ", campsiteFacilitie));
		campsiteDto.setCampsiteTheme(String.join(", ", campsiteTheme));
		Campsite campsite = campsiteDto.createCampsite();
		
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
	
	public Optional<Campsite> findByCampsiteId(Long campsiteId) {
		return campsiteRepository.findById(campsiteId);
	}
	
	public List<Campsite> findAllCampsite() {
		return campsiteRepository.findAll();
	}
	
}
