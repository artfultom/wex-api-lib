package my.artfultom.wexapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.artfultom.wexapi.dto.Depth;
import my.artfultom.wexapi.dto.Info;
import my.artfultom.wexapi.dto.Ticker;
import my.artfultom.wexapi.dto.Trade;

import java.io.IOException;
import java.util.*;

public class PublicApi {

    private static final int PUBLIC_API_DEFAULT_VERSION = 3;

    private int version = PUBLIC_API_DEFAULT_VERSION;

    private GetRequest request;

    public PublicApi(GetRequest request) {
        request.append(String.valueOf(version));

        this.request = request;
    }

    public PublicApi(GetRequest request, int version) {
        request.append(String.valueOf(version));

        this.request = request;
        this.version = version;
    }

    public InfoExecutor getInfo() throws IOException {
        this.request.append("info");

        return new InfoExecutor();
    }

    public TickerExecutor getTicker(String pair) throws IOException {
        this.request.append("ticker");
        this.request.append(pair);

        return new TickerExecutor();
    }

    public TickerExecutor getTicker(List<String> pairs) throws IOException {
        this.request.append("ticker");
        this.request.append(String.join("-", pairs));

        return new TickerExecutor();
    }

    public DepthExecutor getDepth(String pair) throws IOException {
        this.request.append("depth");
        this.request.append(pair);

        return new DepthExecutor();
    }

    public DepthExecutor getDepth(List<String> pairs) throws IOException {
        this.request.append("depth");
        this.request.append(String.join("-", pairs));

        return new DepthExecutor();
    }

    public TradeExecutor getTrade(String pair) throws IOException {
        this.request.append("trades");
        this.request.append(pair);

        return new TradeExecutor();
    }

    public TradeExecutor getTrade(List<String> pairs) throws IOException {
        this.request.append("trades");
        this.request.append(String.join("-", pairs));

        return new TradeExecutor();
    }

    public interface Executor<T> {
        T execute() throws IOException;
    }

    public class InfoExecutor implements Executor<Info> {

        @Override
        public Info execute() throws IOException {
            String responseStr = request.execute();

            return new ObjectMapper().readValue(responseStr, Info.class);
        }
    }

    public class TickerExecutor implements Executor<Map<String, Ticker>> {

        public TickerExecutor ignoreInvalid() {
            request.addParameter("ignore_invalid", Boolean.TRUE.toString().toLowerCase());

            return this;
        }

        @Override
        public Map<String, Ticker> execute() throws IOException {
            Map<String, Ticker> result = new HashMap<>();

            String responseStr = request.execute();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseJson = mapper.readTree(responseStr);

            for (Iterator<String> it = responseJson.fieldNames(); it.hasNext(); ) {
                String pairName = it.next();
                Ticker ticker = mapper.readValue(responseJson.get(pairName).toString(), Ticker.class);

                result.put(pairName, ticker);
            }

            return result;
        }
    }

    public class DepthExecutor implements Executor<Map<String, Depth>> {

        public DepthExecutor setLimit(int limit) {
            request.addParameter("limit", Integer.toString(limit));

            return this;
        }

        public DepthExecutor ignoreInvalid() {
            request.addParameter("ignore_invalid", Boolean.TRUE.toString().toLowerCase());

            return this;
        }

        @Override
        public Map<String, Depth> execute() throws IOException {
            Map<String, Depth> result = new HashMap<>();

            String responseStr = request.execute();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseJson = mapper.readTree(responseStr);

            for (Iterator<String> it = responseJson.fieldNames(); it.hasNext(); ) {
                String pairName = it.next();
                Depth depth = mapper.readValue(responseJson.get(pairName).toString(), Depth.class);

                result.put(pairName, depth);
            }

            return result;
        }
    }

    public class TradeExecutor implements Executor<Map<String, List<Trade>>> {

        public TradeExecutor setLimit(int limit) {
            request.addParameter("limit", Integer.toString(limit));

            return this;
        }

        public TradeExecutor ignoreInvalid() {
            request.addParameter("ignore_invalid", Boolean.TRUE.toString().toLowerCase());

            return this;
        }

        @Override
        public Map<String, List<Trade>> execute() throws IOException {
            Map<String, List<Trade>> result = new HashMap<>();

            String responseStr = request.execute();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseJson = mapper.readTree(responseStr);

            for (Iterator<String> it = responseJson.fieldNames(); it.hasNext(); ) {
                String pairName = it.next();

                JsonNode tradeListJson = responseJson.get(pairName);

                List<Trade> tradeList = new ArrayList<>();

                if (tradeListJson.isArray()) {
                    for (JsonNode tradeJson : tradeListJson) {
                        Trade trade = mapper.readValue(tradeJson.toString(), Trade.class);

                        tradeList.add(trade);
                    }
                }

                result.put(pairName, tradeList);
            }

            return result;
        }
    }
}
