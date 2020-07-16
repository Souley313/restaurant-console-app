package dev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;

@SpringJUnitConfig(classes = { JdbcTestConfig.class, PlatDaoJdbc.class })
@ActiveProfiles("jdbc")
public class PlatDoaJdbcIntegrationTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	PlatDaoJdbc platDaoJdbc;

	@Test
	public void listerPlatsNonVide() {
		int resultat = platDaoJdbc.listerPlats().size();
		assertEquals(7, resultat);
	}

	@Test
	public void ajouter() {
		platDaoJdbc.ajouterPlat("Taboulé", 4200);
		String sql = "Select * from plat where nom= 'Taboulé'";
		Plat resltt = jdbcTemplate.queryForObject(sql, new PlatRowMapper());
		Assertions.assertThat(resltt.getNom()).isEqualTo("Taboulé");
	}

}