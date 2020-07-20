package com.game.roullet.repository;

import com.game.roullet.entity.Spin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpinRepository extends JpaRepository<Spin, Integer> {
}
