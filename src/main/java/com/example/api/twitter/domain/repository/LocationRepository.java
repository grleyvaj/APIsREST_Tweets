package com.example.api.twitter.domain.repository;

import com.example.api.twitter.domain.entity.LocationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface LocationRepository {

    Page<LocationEntity> findAll(Pageable pageable);

    List<LocationEntity> findAll();

    void save(LocationEntity locationEntity);

    Optional<LocationEntity> findOne(long locationId);
}
