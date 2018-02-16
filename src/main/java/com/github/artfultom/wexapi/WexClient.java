package com.github.artfultom.wexapi;

import com.github.artfultom.wexapi.exception.ReadOnlyException;
import com.github.artfultom.wexapi.publicapi.PublicApi;
import com.github.artfultom.wexapi.pushapi.PushApi;
import com.github.artfultom.wexapi.request.AuthorizedPostRequest;
import com.github.artfultom.wexapi.request.GetRequest;
import com.github.artfultom.wexapi.tradeapi.TradeApi;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;

public class WexClient implements Closeable {

    private CloseableHttpClient httpClient;

    private String url;

    private String key;

    private String secret;

    private Integer nonce = 1;

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
        return new PublicApi(new GetRequest(this));
    }

    public PublicApi publicApi(int version) {
        return new PublicApi(new GetRequest(this), version);
    }

    public TradeApi tradeApi() {
        if (this.key == null && this.secret == null) {
            throw new ReadOnlyException();
        }

        return new TradeApi(new AuthorizedPostRequest(this));
    }

    public PushApi pushApi() {
        return new PushApi();
    }

    public PushApi pushApi(String cluster, String key) {
        return new PushApi(cluster, key);
    }

    @Override
    public void close() throws IOException {
        if (this.httpClient != null) {
            this.httpClient.close();
        }
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public String getSecret() {
        return secret;
    }

    public Integer getNonce() {
        return nonce;
    }

    public void setNonce(Integer nonce) {
        this.nonce = nonce;
    }
}
