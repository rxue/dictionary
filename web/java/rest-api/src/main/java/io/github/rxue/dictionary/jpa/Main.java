package io.github.rxue.dictionary.jpa;

import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Generate database schema
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Going to generate database schema!");
        System.out.println("test property value is " + System.getProperty("test"));
        Persistence.generateSchema("dictionary", overwrittenHibernateAndJPAProperties());
    }
    private static Map overwrittenHibernateAndJPAProperties() {
        Properties allSystemProperties = System.getProperties();
        Map overwrittenProperties = new HashMap();
        allSystemProperties.forEach((p, v) -> {
            if (p instanceof String stringProperty &&
                    (stringProperty.startsWith("jakarta") || stringProperty.startsWith("hibernate"))) overwrittenProperties.put(stringProperty, v);
        });
        return overwrittenProperties;
    }
}
