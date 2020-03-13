package br.com.brainweb.interview.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.brainweb.interview.model.Hero;


@Repository
public interface HeroRepository extends JpaRepository<Hero, Long>{

	@Query("select h from Hero h where h.id =:id")
	public Hero findHeroById(@Param("id") Long id);

	@Query("select h from Hero h where h.name like %:nome%")
	public List<Hero> findByName(@Param("nome") String nome);
}
