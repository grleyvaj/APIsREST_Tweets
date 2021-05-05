package com.example.api.twitter.infrastructure.redis;

import com.example.api.twitter.domain.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Gloria R. Leyva Jerez
 */
@Repository
public interface LocationJPARepository extends JpaRepository<LocationEntity, Long>, JpaSpecificationExecutor<LocationEntity> {
}
