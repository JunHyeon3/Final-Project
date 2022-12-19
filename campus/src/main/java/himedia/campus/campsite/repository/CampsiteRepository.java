package himedia.campus.campsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import himedia.campus.campsite.entity.Campsite;

public interface CampsiteRepository extends JpaRepository<Campsite, Long> {

}
