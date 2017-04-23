package demo.quartz.cluster;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import demo.quartz.cluster.quartz.QuartzConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import javax.sql.DataSource;
import java.util.Properties;

@EnableAutoConfiguration(exclude=HiddenHttpMethodFilter.class)
@Configuration
@Import(QuartzConfig.class)
public class AppConfig {

    @Bean
    @Autowired
    public JdbcTemplate JdbcTemplate(DataSource dataSource){
        JdbcTemplate bean = new JdbcTemplate(dataSource);
        return bean;
    }

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig(loadDBConfig());
        return new HikariDataSource(config);
    }

    private Properties loadDBConfig() {
        return Utils.readConfig("db.properties");
    }

    @Bean
    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public CustomEmbeddedServletContainerCustomizer customizationBean(){
        return new CustomEmbeddedServletContainerCustomizer();
    }

    public class CustomEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer {

        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.setPort(8081);
        }

    }
}
