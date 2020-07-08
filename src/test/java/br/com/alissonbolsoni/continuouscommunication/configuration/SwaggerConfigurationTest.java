package br.com.alissonbolsoni.continuouscommunication.configuration;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigurationTest {

    @Test
    void testCreateApi(){
        SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
        Docket api = swaggerConfiguration.api();

        assertNotNull(api);
        assertTrue(api.getClass().isAssignableFrom(Docket.class));
    }

}