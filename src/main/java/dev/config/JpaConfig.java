package dev.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.*;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("jpa") //configurer un profil pour JPA
@EnableTransactionManagement //ajouter la configuration qui permet d'utiliser l'annotation @Transactional
public class JpaConfig {
	
	//Configuration de l'EntityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		//vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true); // activer les logs SQL
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setJpaVendorAdapter(vendorAdapter);
		emf.setPackagesToScan("dev.entite");
		emf.setDataSource(dataSource);
		emf.afterPropertiesSet();
		return emf;
	}

	//Configuration du gestionnaire de transaction pour JPA
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
}
