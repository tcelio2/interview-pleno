package br.com.brainweb.interview.core.validators;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.brainweb.interview.core.utils.ErrorMsg;
import br.com.brainweb.interview.model.PowerStats;

@Component
public class PowerStatsValidator {

	//@Autowired
	//private ValidatorService validatorService;//strength agility dexteriry intelligence

	public ErrorMsg isValid(PowerStats power) {
		ErrorMsg msg = null;

		if(Objects.nonNull(power)){
			if(Objects.isNull(power.getStrength())) {
				return ErrorMsg.builder().field("strength").message("Campo não pode ser null ou vazio.").build();
			}
			if(Objects.isNull(power.getAgility())) {
				return ErrorMsg.builder().field("agility").message("Campo não pode ser null ou vazio.").build();
			}
			if(Objects.isNull(power.getDexteriry())) {
				return ErrorMsg.builder().field("dexteriry").message("Campo não pode ser null ou vazio.").build();
			}
			if(Objects.isNull(power.getIntelligence())) {
				return ErrorMsg.builder().field("intelligence").message("Campo não pode ser null ou vazio.").build();
			}
		}

		return msg;
	}
}
