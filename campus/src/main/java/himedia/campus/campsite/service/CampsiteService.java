package himedia.campus.campsite.service;

import java.io.IOException;
import java.util.List;

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
							List<MultipartFile> campsiteImgFiles) throws IOException, Exception {
		campsiteDto.setCampsiteEnvironment(String.join(", ", campsiteEnvironment));
		campsiteDto.setCampsiteFacilitie(String.join(", ", campsiteFacilitie));
		campsiteDto.setCampsiteTheme(String.join(", ", campsiteTheme));
		Campsite campsite = campsiteDto.createCampsite();
		campsiteRepository.save(campsite);
		
		for(MultipartFile mf: campsiteImgFiles) {
			CampsiteImg campsiteImg = new CampsiteImg();
			campsiteImg.setCampsite(campsite);
			campsiteImgService.saveCampsiteImg(campsiteImg, mf);
		}
		
		return campsite.getCampsiteId(); 
	}
	
}
