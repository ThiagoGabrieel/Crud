package com.thiago.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {
    private static final Properties props = new Properties();

    static {
        try(InputStream input = DbConfig.class.getClassLoader()
                .getResourceAsStream("db.properties")){

            if(input == null){
                throw new RuntimeException(
                        "Arquivo db.properties não encontrado. Copie db.properties.example e preencha os dados."
                );
            }
            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar db.properties");
        }
    }
    public static String getUrl(){
        return props.getProperty("db.url");
    }

    public static String getUsername(){
        return props.getProperty("db.username");
    }
    public static String getPassword(){
        return props.getProperty("db.password");
    }
}
