package my.artfultom.wexapi;

import my.artfultom.wexapi.request.AuthorizedPostRequest;

import java.io.IOException;

public class TradeApi {

    private static final String TRADE_API_PREFIX = "tapi";

    private AuthorizedPostRequest request;

    public TradeApi(AuthorizedPostRequest request) {
        request.append(TRADE_API_PREFIX);

        this.request = request;
    }

    public String getInfo() throws IOException {
        this.request.addParameter("nonce", "212372975");
        this.request.addParameter("method", "getInfo");

        return this.request.execute();
    }

    public interface Executor<T> {
        T execute() throws IOException;
    }
}
