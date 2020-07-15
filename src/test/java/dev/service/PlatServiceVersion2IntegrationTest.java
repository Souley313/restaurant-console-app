package dev.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.dao.PlatDaoFichier;
import dev.exception.PlatException;

//@ContextConfiguration(classes = PlatServiceVersion2.class)
//@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {PlatServiceVersion2.class, PlatDaoFichier.class})
@ActiveProfiles({"service2", "fichier"}) 
public class PlatServiceVersion2IntegrationTest {
	
	@Autowired
	PlatServiceVersion2 platServiceVersion2;
	
	@Test
	void ajouterPlatValide() {
		platServiceVersion2.ajouterPlat("RizAupoisson", 6200);
	}

	@Test
	void ajouterPlatPrixInvalide() {
		assertThrows(PlatException.class, () -> platServiceVersion2.ajouterPlat("RizAupoisson", 120), "le prix d'un plat doit être supérieur à 5 €");
	}

}
