package TheuxZn16.com.github.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class YamlConverter2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {
  protected YamlConverter2HttpMessageConverter() {
    super(new YAMLMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType("application/yaml"));
  }

}