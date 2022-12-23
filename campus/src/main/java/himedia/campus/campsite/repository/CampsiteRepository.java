package himedia.campus.campsite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.campsite.entity.Campsite;

public interface CampsiteRepository extends JpaRepository<Campsite, Long> {

	Optional<Campsite> findByCampsiteManager(String campsiteManager);

	Optional<Campsite> findByCampsiteId(Long campsiteId);
	
}
