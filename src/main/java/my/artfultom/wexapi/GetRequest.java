package my.artfultom.wexapi;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetRequest {

    private CloseableHttpClient httpClient;

    private String url;

    private Map<String, String> parameters;

    public GetRequest(CloseableHttpClient httpClient, String url) {
        this.httpClient = httpClient;

        if (url == null) {
            this.url = "";
        } else {
            this.url = url;
        }

        parameters = new HashMap<>();
    }

    public String append(String part) {
        if (this.url.endsWith("/")) {
            this.url = this.url.substring("/".length());
        }

        if (part.startsWith("/")) {
            part = part.substring("/".length());
        }

        this.url = this.url.concat("/").concat(part);

        return this.url;
    }

    public void addParameter(String name, String value) {
        parameters.put(name, value);
    }

    public String execute() throws IOException {
        HttpGet httpGet = new HttpGet(getEntireUrl());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity);
    }

    private String getEntireUrl() {
        String result = url;

        if (parameters.size() > 0) {
            List<String> parameterList = parameters.entrySet().stream().map(entry -> {
                if (entry.getValue() != null && !entry.getValue().equals("")) {
                    return entry.getKey().concat("=").concat(entry.getValue());
                } else {
                    return entry.getKey();
                }
            }).collect(Collectors.toList());

            result = result.concat("?").concat(String.join("&", parameterList));
        }

        return result;
    }
}
