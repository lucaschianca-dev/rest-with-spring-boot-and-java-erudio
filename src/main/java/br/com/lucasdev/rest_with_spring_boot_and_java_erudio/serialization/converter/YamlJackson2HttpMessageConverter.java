package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

//    public YamlJackson2HttpMessageConverter() {
//        super(new com.fasterxml.jackson.dataformat.yaml.YAMLMapper(),
//              org.springframework.http.MediaType.parseMediaType("application/x-yaml"));
//    }

    public YamlJackson2HttpMessageConverter() {
        super(
                new YAMLMapper()
                        .setSerializationInclusion(
                                JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/x-yaml")
        );
    }
}
