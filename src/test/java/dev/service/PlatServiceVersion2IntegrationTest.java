package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.AppConfig;
import dev.dao.*;
import dev.exception.PlatException;

//@ContextConfiguration(classes = PlatServiceVersion2.class)
//@ExtendWith(SpringExtension.class)
@SpringJUnitConfig(classes = {AppConfig.class})
@ActiveProfiles({"service2", "memoire"})
public class PlatServiceVersion2IntegrationTest {
	
	@Autowired
	PlatServiceVersion2 platServiceVersion2;
	
	@Test
	void ajouterPlatValide() {
		platServiceVersion2.ajouterPlat("RizAupoisson", 6200);
	}

	@Test
	void ajouterPlatPrixInvalide() {
		assertThatThrownBy(() -> platServiceVersion2.ajouterPlat("RizAupoisson", 43))
		.isInstanceOf(PlatException.class)
		.hasMessage("le prix d'un plat doit être supérieur à 10 €");}

}
