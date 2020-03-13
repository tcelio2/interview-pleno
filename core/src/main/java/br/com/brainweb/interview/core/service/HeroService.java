package br.com.brainweb.interview.core.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brainweb.interview.core.repository.HeroRepository;
import br.com.brainweb.interview.core.repository.PowerStatsRepository;
import br.com.brainweb.interview.core.utils.ErrorMsg;
import br.com.brainweb.interview.core.validators.HeroValidator;
import br.com.brainweb.interview.core.validators.PowerStatsValidator;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;


@Service
public class HeroService {

	Logger log = LoggerFactory.getLogger(HeroService.class);
	
	@Autowired
	private HeroValidator heroValidator;

	@Autowired
	private PowerStatsValidator powerValidator;

	@Autowired
	private HeroRepository heroRepository;

	@Autowired
	private PowerStatsRepository powerStatsRepository;


	public List<Hero> trazerTodosHerois() {
		log.info("Iniciando processo para trazer todos os herois.");
		return heroRepository.findAll();
	}

	public Hero trazerHero(Long id) {
		return heroRepository.findHeroById(id);
	}

	public ErrorMsg gravarUmHero(Hero hero) {
		log.info("Iniciando processo para gravar um heroi.");
		PowerStats power = verificarExistenciaPower(hero);
		if(Objects.isNull(power)) {
			ErrorMsg valid = verificarValidaPower(hero.getPower());	
			if(Objects.isNull(valid)) {
				ErrorMsg valid2 = heroValidator.isValid(hero);
				if(Objects.isNull(valid2)) {
					PowerStats newPower = criarUmNovoPower(hero.getPower());
					hero.setPower(newPower);
					heroRepository.save(hero);
				} else {
					return valid2;
				}
			} else {
				return valid;
			}
		} else {
			ErrorMsg valid2 = heroValidator.isValid(hero);
			if(Objects.isNull(valid2)) {
				hero.setPower(power);
				heroRepository.save(hero);
			} else {
				return valid2;
			}
			
		}
		return null;
	}

	private PowerStats criarUmNovoPower(PowerStats power) {
		return powerStatsRepository.save(power);
		
	}

	private PowerStats verificarExistenciaPower(Hero hero) {
		if(Objects.nonNull(hero.getPower()) && Objects.nonNull(hero.getPower().getId())){
			return powerStatsRepository.getOne(hero.getPower().getId());
		}
		return null;
	}

	public ErrorMsg atualizarUmHero(Hero hero) {
		log.info("Iniciando processo para atualizar um hero.");
		if(Objects.nonNull(hero.getId())) {
			Optional<Hero> heroObj = heroRepository.findById(hero.getId());
			if(heroObj.isPresent()) {
				ErrorMsg valid = heroValidator.isValid(hero);
				if(Objects.isNull(valid)){
					hero.setCreatedAt(heroObj.get().getCreatedAt());
					heroRepository.save(hero);					
				} else {
					return valid;
				}

			}
		}
		return null;
	}

	public List<Hero> buscarPorNomeHero(String nome) {
		return heroRepository.findByName(nome);
	}

	public Hero deleteHero(Long id) {
		log.info("Iniciando processo para deletar um hero.");
		Optional<Hero> hero = heroRepository.findById(id);
		if(hero.isPresent()){
			heroRepository.deleteById(id);
			return hero.get();
		}
		return null;
	}

	private ErrorMsg verificarValidaPower(PowerStats powerStats) {
		if(Objects.isNull(powerStats.getId())) {
			return powerValidator.isValid(powerStats);
		}
		return null;
	}

}
