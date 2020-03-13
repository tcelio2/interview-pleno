package br.com.brainweb.interview.core.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.brainweb.interview.core.repository.HeroRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

public class HeroServiceTest {
	
	
	@InjectMocks
	private HeroService heroService;
	
	@Mock
	private HeroRepository heroRepository;
	
	List<Hero> heroList = null;
	
	PowerStats power1 = null;
	
	Hero hero1 = null;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		
		heroList = new ArrayList<>();
		
		power1 = PowerStats.builder()
					.agility(10)
					.dexteriry(10)
					.intelligence(10)
					.strength(10)
					.id(1L).build();
		
		hero1 = Hero.builder()
						.id(1L)
						.name("Batman")
						.race("HUMAN")
						.power(power1).build();
		
		heroList.add(hero1);
		
		
	}

	
	@Test
	public void testTrazerTodosHerois() {
		
		Mockito.when(heroRepository.findAll()).thenReturn(heroList);
		List<Hero> todosHerois = heroService.trazerTodosHerois();
		
		Assert.assertFalse(todosHerois.isEmpty());
		Assert.assertEquals(1, todosHerois.size());
		
	}
	
	@Test
	public void testTrazerHero() {
		
		Mockito.when(heroRepository.findHeroById(1L)).thenReturn(hero1);
		Hero heroTest = heroService.trazerHero(1L);
		
		Assert.assertEquals("Batman", heroTest.getName());
		Assert.assertEquals("HUMAN", heroTest.getRace());
		
		
	}
	
}
