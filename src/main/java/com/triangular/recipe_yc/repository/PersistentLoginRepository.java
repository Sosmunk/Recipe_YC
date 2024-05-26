package com.triangular.recipe_yc.repository;

import com.triangular.recipe_yc.entity.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, UUID> {
    Optional<PersistentLogin> findBySeries(String series);
    boolean existsBySeries(String series);
    void deleteAllByUsername(String username);
}
