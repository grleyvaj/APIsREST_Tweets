package com.example.api.twitter.infrastructure.redis;

import com.example.api.twitter.domain.entity.LocationEntity;
import com.example.api.twitter.domain.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
@CacheConfig(cacheNames = "locationRepository")
public class LocationRedisRepositoryImpl implements LocationRepository {

    private final LocationJPARepository locationJPARepository;

    @Autowired
    public LocationRedisRepositoryImpl(LocationJPARepository locationJPARepository) {
        this.locationJPARepository = locationJPARepository;
    }

    @Override
    @Cacheable(value = "locationData")
    public Page<LocationEntity> findAll(Pageable pageable) {
        return locationJPARepository.findAll(pageable);
    }

    @Override
    public List<LocationEntity> findAll() {
        return locationJPARepository.findAll();
    }

    @Override
    public void save(LocationEntity locationEntity) {
        locationJPARepository.save(locationEntity);
    }

    @Override
    @Cacheable(value = "locationData", key = "#id")
    public Optional<LocationEntity> findOne(long locationId) {
        return locationJPARepository.findById(locationId);
    }
}
