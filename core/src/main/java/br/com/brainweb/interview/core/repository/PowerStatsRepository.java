package br.com.brainweb.interview.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brainweb.interview.model.PowerStats;

@Repository
public interface PowerStatsRepository extends JpaRepository<PowerStats, Long>{
}
