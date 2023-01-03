package himedia.campus.repository.campsite;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.entity.campsite.Campsite;

public interface CampsiteRepository extends JpaRepository<Campsite, Long> {

	Optional<Campsite> findByCampsiteManager(String campsiteManager);

	Optional<Campsite> findByCampsiteId(Long campsiteId);

	Page<Campsite> findByCampsiteEnvironmentContainingAndCampsiteThemeContaining(String campsiteEnvironment, String campsiteTheme, Pageable pageable);
	
}
