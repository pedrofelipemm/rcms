package br.ufscar.rcms.view.util;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.ufscar.rcms.commons.util.JsonUtil;
import br.ufscar.rcms.scorecard.commons.rest.Wrapper;

@Component
public class RestClient extends RestTemplate {

    public <T> List<T> getForEntity(final String url, final Type t, final Object... urlVariables) {

        List<T> result = null;
        ResponseEntity<String> response = getForEntity(url, String.class, urlVariables);

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            Wrapper<T> wrapper = JsonUtil.getInstance().fromJson(response.getBody(), t);
            result = wrapper.getData();
        }

        return result;
    }
}