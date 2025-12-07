package com.nailton.spring_render.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

import javax.sql.DataSource;

@Configuration
public class DotenvConfig {
    
    @Bean
    public DataSource dataSource() {
        Dotenv dotenv = Dotenv.configure()
                .directory("./")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
        
        String dbUrl = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        
        // Validações básicas
        if (dbUrl == null ) {
            throw new IllegalStateException("Variáveis de ambiente do banco de dados não configuradas corretamente");
        }
        
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(dbUrl)
               .username(username)
               .password(password)
                .build();
    }
    
    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("./")
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();
    }
}