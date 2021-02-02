package bookshop_system.app.config;

import bookshop_system.app.util.ReaderUtil;
import bookshop_system.app.util.ReaderUtilImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ReaderUtil reader(){
        return new ReaderUtilImpl();
    }
}
