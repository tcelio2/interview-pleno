package br.com.brainweb.interview.core.configuration;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
//@EnableAutoConfiguration(
//exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class JdbcConfiguration {

//    //@Value("${spring.datasource.url:jdbc:postgresql://localhost:5432/postgres}")
//	//@Value("#{systemProperties['spring.datasource.url'] ?: 'jdbc:postgresql://localhost:5432/postgres'}")
//    @Value("${spring.datasource.url}")
//    private String jdbcUrl;
//
//    //@Value("${spring.datasource.username:brainweb}")
//	//@Value("#{systemProperties['spring.datasource.username'] ?: 'brainweb'}")
//    @Value("${spring.datasource.username}")
//    private String username;
//
//    //@Value("${spring.datasource.password:brainweb}")
//	//@Value("#{systemProperties['spring.datasource.password'] ?: 'brainweb'}")
//    @Value("${spring.datasource.password}")
//    private String password;
//
//    //@Value("${spring.datasource.schema:interview_service}")
//	//@Value("#{systemProperties['spring.datasource.schema'] ?: 'interview_service'}")
//    @Value("${spring.datasource.schema}")
//    private String schema;
//
//    //@Autowired
//    //private Environment env;
    
    @Bean
    public DataSource dataSource() {
    	
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/heros");
        dataSource.setUsername("brainweb");
        dataSource.setPassword("brainweb");
        dataSource.setMaximumPoolSize(getMaxPoolSize());
        dataSource.setConnectionTimeout(TimeUnit.SECONDS.toMillis(5L));
        dataSource.setSchema("interview_service");
        
        return dataSource;
    }

    @Bean(name="transactionManager")
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }
    
   /* @Bean
    public FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
    	return new FlywayMigrationInitializer(flyway, (f) -> {});
    }*/

    /**
     * Identifies how many connections can be opened based on Postgres recommended formula.
     *
     * @return pool size capacity
     * @see <a href="https://wiki.postgresql.org/wiki/Number_Of_Database_Connections#How_to_Find_the_Optimal_Database_Connection_Pool_Size" />
     */
    private int getMaxPoolSize() {
        return (Runtime.getRuntime().availableProcessors() * 2) + 1;
    }
}
