package br.com.brainweb.interview.core.service;

import static org.mockito.Mockito.mockitoSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.brainweb.interview.core.repository.HeroRepository;

public class HeroServiceTest {
	
	
	@InjectMocks
	private HeroService heroService;
	
	@Autowired
	private HeroRepository heroRepository;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void testTrazerTodosHerois() {
		
		//Mockito.when(heroRepository.)
		
		
		
		
	}
	
}
