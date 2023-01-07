package himedia.campus.repository.campsite;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.campus.entity.campsite.Campsite;

public interface CampsiteRepository extends JpaRepository<Campsite, Long> {

	Optional<Campsite> findByCampsiteManager(String campsiteManager);

	Optional<Campsite> findByCampsiteId(Long campsiteId);
	
	@Query(value = "SELECT * FROM CAMPSITE C WHERE "
			+ "C.CAMPSITE_ID IN (SELECT CAMPSITE_ID FROM THEMES WHERE THEMES LIKE %?%) "
			+ "AND "
			+ "C.CAMPSITE_ID IN (SELECT CAMPSITE_ID FROM ENVIRONMENTS WHERE ENVIRONMENTS LIKE %?%)", 
			nativeQuery = true)
	Page<Campsite> findByThemeAndEnvironment(String searchTheme, String searchEnvironment, Pageable pageable);
	
}
