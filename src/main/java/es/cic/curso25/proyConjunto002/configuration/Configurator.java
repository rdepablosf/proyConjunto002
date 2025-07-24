package es.cic.curso25.proyConjunto002.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mifichero.properties")
public class Configurator {
    
    @Value("${muestrafuncionalidad}")
    private Integer muestrafuncionalidad;
}
