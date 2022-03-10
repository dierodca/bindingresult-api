package co.com.carvajal.platform.crosscutting.patterns.builders;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import co.com.carvajal.platform.crosscutting.patterns.singleton.RestTemplateSingleton;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Builder para ejecutar restTemplate
 *
 * @author dierodca
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestTemplateBuilder {

    private String url;
    private Object data;

    public static RestTemplateBuilder builder() {
        return new RestTemplateBuilder();
    }

    public RestTemplateBuilder withUrl(final String url) {
        this.url = url;
        return this;
    }

    public RestTemplateBuilder withData(final Object data) {
        this.data = data;
        return this;
    }

    public <T> T buildBasicGET(final Class<T> responseObject) {
        return RestTemplateSingleton.getInstance().getForObject(this.url, responseObject);
    }

    public <T> T buildPost(final Class<T> responseObject) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<Object> request = new HttpEntity<>(this.data, headers);
        return RestTemplateSingleton.getInstance().postForObject(this.url, request, responseObject);
    }
}
