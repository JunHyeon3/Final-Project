package himedia.campus.repository.campsite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import himedia.campus.entity.campsite.CampsiteImg;

public interface CampsiteImgRepository extends JpaRepository<CampsiteImg, Long> {
	@Query(value = "select campsite_img_path from campsite_img where campsite_id=?", nativeQuery = true)
	List<String> findAllCampsiteImgPath(Long campsiteId);

	List<CampsiteImg> findByCampsite_CampsiteId(Long campsiteId);
}
