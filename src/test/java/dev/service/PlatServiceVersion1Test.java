package dev.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.IPlatDao;
import dev.exception.PlatException;

class PlatServiceVersion1Test {
	
	IPlatDao dao;
	private PlatServiceVersion1 platServiceVersion1;
	
	@BeforeEach
	void setUp() {
		dao = mock(IPlatDao.class);
		this.platServiceVersion1 = new PlatServiceVersion1(dao);

	}

	@Test
	void ajouterPlatNomInvalide() {
		assertThrows(PlatException.class, () -> platServiceVersion1.ajouterPlat("Riz", 5200), "un plat doit avoir un nom de plus de 3 caractères");
		
	}
	
	@Test
	void ajouterPlatPrixInvalide() {
		assertThrows(PlatException.class, () -> platServiceVersion1.ajouterPlat("Riz Au poisson", 120), "le prix d'un plat doit être supérieur à 5 €");
		
	}
	
	@Test
	void ajouterPlatValide() {
		platServiceVersion1.ajouterPlat("Riz Au poisson", 5200);
		verify(dao).ajouterPlat("Riz Au poisson", 5200);

	}

}
