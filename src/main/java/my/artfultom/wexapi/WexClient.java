package my.artfultom.wexapi;

import my.artfultom.wexapi.exception.ReadOnlyException;
import my.artfultom.wexapi.request.AuthorizedPostRequest;
import my.artfultom.wexapi.request.GetRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;

public class WexClient implements Closeable {

    private CloseableHttpClient httpClient;

    private String url;
    private String key;
    private String secret;
    private int nonce;

    public WexClient(String url) {
        this.httpClient = HttpClients.createDefault();
        this.url = url;
    }

    public WexClient(String url, String key, String secret) {
        this.httpClient = HttpClients.createDefault();
        this.url = url;
        this.key = key;
        this.secret = secret;
    }

    public WexClient(String url, String key, String secret, int nonce) {
        this.httpClient = HttpClients.createDefault();
        this.url = url;
        this.key = key;
        this.secret = secret;
        this.nonce = nonce;
    }

    public PublicApi publicApi() {
        return new PublicApi(new GetRequest(this.httpClient, this.url));
    }

    public PublicApi publicApi(int version) {
        return new PublicApi(new GetRequest(this.httpClient, this.url), version);
    }

    public TradeApi tradeApi() {
        if (this.key == null && this.secret == null) {
            throw new ReadOnlyException();
        }

        return new TradeApi(new AuthorizedPostRequest(this.httpClient, this.url, this.key, this.secret));
    }

    @Override
    public void close() throws IOException {
        if (this.httpClient != null) {
            this.httpClient.close();
        }
    }
}
