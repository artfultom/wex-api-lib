package my.artfultom.wexapi;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;

public class WexClient implements Closeable {

    private CloseableHttpClient httpClient;

    private String url;

    public WexClient(String url) {
        httpClient = HttpClients.createDefault();
        this.url = url;
    }

    public PublicApi publicApi() {
        return new PublicApi(new GetRequest(httpClient, url));
    }

    public PublicApi publicApi(int version) {
        return new PublicApi(new GetRequest(httpClient, url), version);
    }

    @Override
    public void close() throws IOException {
        if (httpClient != null) {
            httpClient.close();
        }
    }
}
