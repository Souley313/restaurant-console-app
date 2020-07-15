package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;

@SpringJUnitConfig(classes = {PlatDaoFichier.class})
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDoaFichierTest {
	
	@Autowired
	PlatDaoFichier dao;
	
	@Test
	void ajouterPlatSauvegarde1() {
		dao.ajouterPlat("pizzas", 1600);
		List<Plat> rsltt = dao.listerPlats();
		assertThat(rsltt).extracting(Plat::getNom).containsExactly("pizzas");
		assertThat(rsltt).extracting(Plat::getPrixEnCentimesEuros).containsExactly(1600);
		assertThat(rsltt).hasSize(1);
		
	}

	@Test
	void ajouterPlatSauvegarde2() {
		dao.ajouterPlat("bolognaise", 3000);
		List<Plat> rsltt = dao.listerPlats();
		assertThat(rsltt).extracting(Plat::getNom).containsExactly("bolognaise");
		assertThat(rsltt).extracting(Plat::getPrixEnCentimesEuros).containsExactly(3000);
		assertThat(rsltt).hasSize(1);	
	}
	

}
