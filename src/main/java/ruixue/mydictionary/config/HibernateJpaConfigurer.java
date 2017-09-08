package ruixue.mydictionary.config;

import java.sql.SQLException;
import java.util.Properties;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "ruixue.mydictionary.repository")
public class HibernateJpaConfigurer {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		dataSource.setUrl("jdbc:hsqldb:mem:mydictionary");
		//dataSource.setSchema("PUBLIC");
		return dataSource;
	}
	//JPA (standard)
	@Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        factory.setPackagesToScan(new String[]{"ruixue.mydictionary.entity"});
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory.getNativeEntityManagerFactory();
    }
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(emf);
	    return transactionManager;
	}
//	 @Bean
//	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//	      return new PersistenceExceptionTranslationPostProcessor();
//	   }
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "ruixue.dependencies.fix.hibernate5.MyHSQLDialect");
		//properties.put("hibernate.show_sql", "true");
		//properties.put("hibernate.format_sql", "false");
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.hbm2ddl.import_files", "import_dev.sql");
		return properties;
	}
	private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.HSQL);
		vendorAdapter.setShowSql(true);
		//vendorAdapter.setGenerateDdl(true);
        //vendorAdapter.set
        return vendorAdapter;
	}
	
}
