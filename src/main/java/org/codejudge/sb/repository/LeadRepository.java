package org.codejudge.sb.repository;

import org.codejudge.sb.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<LeadEntity, String> {


@Query("select l from LeadEntity l where l.id = ?1")
LeadEntity findById(Integer id);

}