
package app.config;

import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "app.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig {

        @Bean
        public SessionFactory sessionFactory() {
                LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
                builder.scanPackages("app.model").addProperties(getHibernateProperties());
                return builder.buildSessionFactory();
        }

	private Properties getHibernateProperties() {
                Properties properties = new Properties();
                properties.put("hibernate.format_sql", "true");
                properties.put("hibernate.show_sql", "true");
                properties.put("hibernate.hbm2ddl.auto", "update");
                properties.put("hibernate.current_session_context_class", "thread");
                properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                return properties;
        }

	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {

		BasicDataSource basicDataSource = new BasicDataSource();
	        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://localhost:3306/tesst?zeroDateTimeBehavior=convertToNull");
		basicDataSource.setUsername("root");
                basicDataSource.setPassword("root");
		return basicDataSource;
	}

	//Create a transaction manager
	@Bean
        public HibernateTransactionManager txManager() {
                return new HibernateTransactionManager(sessionFactory());
        }

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}