package br.com.brainweb.interview.core.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brainweb.interview.core.service.HeroService;
import br.com.brainweb.interview.core.utils.ErrorMsg;
import br.com.brainweb.interview.model.Hero;

@RestController
@RequestMapping("/hero")
public class HeroController {

	@Autowired
	private HeroService service;
	
	@GetMapping
	public List<Hero> obterHerois() {
		return service.trazerTodosHerois();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Hero> obterHerois(@PathVariable("id") Long id) {
		 Hero r = service.trazerHero(id);
		 if(Objects.isNull(r))
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		 return ResponseEntity.status(HttpStatus.OK).body(r);
	}

	@GetMapping("/busca")
	public List<Hero> obterHeroisPorNome(@RequestParam("name") String nome) {
		return service.buscarPorNomeHero(nome);
	}
	
	@PostMapping
	public ResponseEntity<ErrorMsg> gravarHero(@RequestBody Hero hero) {
		ErrorMsg feedBack = service.gravarUmHero(hero);
		if(Objects.nonNull(feedBack))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(feedBack);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@PutMapping
	public ResponseEntity<ErrorMsg> atualizarHero(@RequestBody Hero hero) {
		ErrorMsg feedBack = service.atualizarUmHero(hero);
		if(Objects.nonNull(feedBack))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(feedBack);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Hero> deleteHero(@PathVariable("id") Long id) {
		 if(Objects.isNull(service.deleteHero(id)))
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		 return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
