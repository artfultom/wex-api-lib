package my.artfultom.wexapi.request;

import org.apache.http.NameValuePair;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericRequest {

    protected CloseableHttpClient httpClient;

    protected String url = "";

    protected List<NameValuePair> parameters = new ArrayList<>();

    public GenericRequest(CloseableHttpClient httpClient, String url) {
        this.httpClient = httpClient;
        this.url = url;
    }

    public GenericRequest append(String part) {
        if (this.url.endsWith("/")) {
            this.url = this.url.substring("/".length());
        }

        if (part.startsWith("/")) {
            part = part.substring("/".length());
        }

        this.url = this.url.concat("/").concat(part);

        return this;
    }

    public GenericRequest addParameter(String name, String value) {
        this.parameters.add(new BasicNameValuePair(name, value));

        return this;
    }

    public abstract String execute() throws IOException;
}
