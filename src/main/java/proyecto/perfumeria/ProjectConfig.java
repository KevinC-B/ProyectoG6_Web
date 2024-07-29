
package proyecto.perfumeria;

import java.util.Locale;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.LocaleResolver;

import org.springframework.context.annotation.Bean;


import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


@Configuration
public class ProjectConfig  implements WebMvcConfigurer{
    @Bean
    public LocaleResolver localeResolver() {
        // Use CookieLocaleResolver or SessionLocaleResolver
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;}
    
     @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
}
