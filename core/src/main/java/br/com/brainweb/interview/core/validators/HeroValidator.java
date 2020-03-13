package br.com.brainweb.interview.core.validators;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brainweb.interview.core.service.ValidatorService;
import br.com.brainweb.interview.core.utils.ErrorMsg;
import br.com.brainweb.interview.core.utils.RaceEnum;
import br.com.brainweb.interview.model.Hero;

@Component
public class HeroValidator {
	
	@Autowired
	private ValidatorService validatorService;
	
	public ErrorMsg isValid(Hero hero) {
		ErrorMsg msg = null;
		
		if(Objects.nonNull(hero)){
			if(Objects.isNull(hero.getName()) || hero.getName().isEmpty()) {
				return ErrorMsg.builder().field("name").message("Nome não pode ser vazio.").build();
			}
			if(Objects.nonNull(hero.getName()) && validatorService.verificarExistenciaMesmoNome(hero)) {
				return ErrorMsg.builder().field("name").message("Já existe um super heroi com este nome.").build();
			}
			if(Objects.nonNull(hero.getRace())) {
				RaceEnum[] values = RaceEnum.values();
				if(!verificarRacesExiste(values,hero.getRace().toString()))
					return ErrorMsg.builder().field("race").message("Raças permitidas: "+Arrays.toString(values)).build();
			}
			if(Objects.isNull(hero.getEnabled())) {
					return ErrorMsg.builder().field("enabled").message("Campo não pode ser null ou vazio.").build();
			}
			if(Objects.nonNull(hero.getPower())) {
				if(!validatorService.verificarPowerStats(hero.getPower().getId()).isPresent()){
					return ErrorMsg.builder().field("power.id").message("Campo não existe.").build();
				}
			}
		}
		
		return msg;
	}
	
	private Boolean verificarRacesExiste(RaceEnum[] values, String race) {
		Boolean existe = false;
		for (RaceEnum raceEnum : values) {
			if(raceEnum.getTipo().matches(race)){
				return true;
			}
		}
		return existe;
	}
	
}
