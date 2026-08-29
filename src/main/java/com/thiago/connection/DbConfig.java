package com.thiago.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = DbConfig.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "Arquivo db.properties não encontrado. Copie db.properties.example e preencha os dados."
                );
            }
            props.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar db.properties");
        }
    }

    public static String getUrl() {
        return props.getProperty("db.url");
    }

    public static String getUser() {
        return props.getProperty("db.user");
    }

    public static String getPassword() {
        return props.getProperty("db.password");
    }

    private static String getRequiredProperty(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Propriedade obrigatoria não encontrada: " + key);
        }
        return value;
    }
}
