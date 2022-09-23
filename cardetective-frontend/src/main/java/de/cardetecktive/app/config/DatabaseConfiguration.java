package de.cardetecktive.app.config;

import de.cardetecktive.app.database.JsChTunnel;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = JsChTunnel.class)
//TODO: database should be created as separate module to avoid code duplication
public class DatabaseConfiguration {

    private static final String JDBC_URL_PATTERN = "jdbc:mysql://%s:%s/%s?serverTimezone=America/New_York";

    @Bean
    public DataSource dataSource(@Value("${jdbc.hostname}") String host,
                                 @Value("${jdbc.local.port}") String port,
                                 @Value("${jdbc.schema}") String schema,
                                 @Value("${jdbc.username}") String username,
                                 @Value("${jdbc.password}") String password) {
        return new SingleConnectionDataSource(getJdbcUrl(host, port, schema), username, password, true);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@NotNull final JsChTunnel jsChTunnel,
                                     @NotNull final DataSource dataSource) {
        jsChTunnel.createSshTunnel();
        return new JdbcTemplate(dataSource);
    }

    private String getJdbcUrl(String host, String port, String schema) {
        final String jdbcUrl = String.format(JDBC_URL_PATTERN, host, port, schema);

        log.info("JDBC url is set to: [{}]", jdbcUrl);
        return jdbcUrl;
    }
}
