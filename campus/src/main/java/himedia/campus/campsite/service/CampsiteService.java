package himedia.campus.campsite.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import himedia.campus.campsite.dto.CampsiteDto;
import himedia.campus.campsite.entity.Campsite;
import himedia.campus.campsite.repository.CampsiteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CampsiteService {

	private final CampsiteRepository campsiteRepository;
	
	public Long saveCampsite(CampsiteDto campsiteDto) {
		Campsite campsite = campsiteDto.createCampsite();
		return campsiteRepository.save(campsite).getCampsiteId(); 
	}
	
}
