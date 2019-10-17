package com.evo.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.h2.tools.Server;
import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Arrays;

@Profile("dev")
@Configuration
public class H2ServerConfig {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

    @PostConstruct
    private void initDb() {
        String sqlStatements[] = {
                "drop table employees if exists",
                "create table employees(id serial,first_name varchar(255),last_name varchar(255))",
                "insert into employees(first_name, last_name) values('Eugen','Paraschiv')",
                "insert into employees(first_name, last_name) values('Scott','Tiger')"
        };

        Arrays.asList(sqlStatements).forEach(sql -> {
            jdbcTemplate.execute(sql);
        });

        // Query test data and print results
    }
}
