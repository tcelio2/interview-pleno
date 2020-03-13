package br.com.brainweb.interview.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brainweb.interview.core.repository.HeroRepository;
import br.com.brainweb.interview.core.repository.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

@Component
public class ValidatorService {

	
	@Autowired
	private PowerStatsRepository powerStatsRepository;
	
	@Autowired
	private HeroRepository heroRepository;
	
	public Optional<PowerStats> verificarPowerStats(Long id) {
		return powerStatsRepository.findById(id);
	}
	
	public Boolean verificarExistenciaMesmoNome(Hero hero) {
		List<Hero> findByName = heroRepository.findByName(hero.getName());
		return !findByName.isEmpty();
	}
}
