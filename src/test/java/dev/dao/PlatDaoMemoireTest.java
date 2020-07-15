package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.*;

import dev.entite.Plat;

class PlatDaoMemoireTest {

	private PlatDaoMemoire platDaoMemoire;

	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void listerPlatsVideALInitialisation() {
		List<Plat> resultat = platDaoMemoire.listerPlats();
        assertThat(resultat.isEmpty());
	}

	@Test
	void ajouterPlatCasPassants() {
		Plat plat = new Plat("Riz au poisson", 5200);
		platDaoMemoire.ajouterPlat(plat.getNom(), plat.getPrixEnCentimesEuros());
		List<Plat> tmp = platDaoMemoire.listerPlats();
		
		assertThat(tmp).extracting(Plat::getNom).contains(plat.getNom());
	}

}
