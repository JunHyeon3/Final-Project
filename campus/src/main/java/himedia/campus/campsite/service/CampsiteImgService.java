package himedia.campus.campsite.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import himedia.campus.campsite.entity.CampsiteImg;
import himedia.campus.campsite.repository.CampsiteImgRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CampsiteImgService {
	@Value("${campsiteImgLocation}")
	private String campsiteImgLocation;

	private final CampsiteImgRepository campsiteImgRepository;

	private final FileService fileService;

	public void saveCampsiteImg(CampsiteImg campsiteImg, MultipartFile campsiteImgFile) throws IOException, Exception  {
		String campsiteImgOriginal = campsiteImgFile.getOriginalFilename();
		String campsiteImgName = "";
		String campsiteImgPath = "";
		
		if (!campsiteImgOriginal.isEmpty()) {
			campsiteImgName = fileService.uploadFile(campsiteImgLocation, campsiteImgOriginal, campsiteImgFile.getBytes());
			campsiteImgPath = "/campus/campsite/" + campsiteImgName;
		}

		campsiteImg.updateCampsiteImg(campsiteImgOriginal, campsiteImgName, campsiteImgPath);
		campsiteImgRepository.save(campsiteImg);
	}
	
	public List<String> findAllCampsiteImgPath(Long campsiteId) {
		return campsiteImgRepository.findAllCampsiteImgPath(campsiteId);
	}

}
