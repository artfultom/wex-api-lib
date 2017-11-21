package my.artfultom.wexapi.publicapi;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.artfultom.wexapi.exception.UnsuccessException;
import my.artfultom.wexapi.publicapi.dto.Depth;
import my.artfultom.wexapi.publicapi.dto.Info;
import my.artfultom.wexapi.publicapi.dto.Ticker;
import my.artfultom.wexapi.publicapi.dto.Trade;
import my.artfultom.wexapi.request.GetRequest;

import java.io.IOException;
import java.util.*;

public class PublicApi {

    private static final int PUBLIC_API_DEFAULT_VERSION = 3;
    private static final String PUBLIC_API_SUFFIX = "api";

    private int version = PUBLIC_API_DEFAULT_VERSION;

    private GetRequest request;

    public PublicApi(GetRequest request) {
        request.append(PUBLIC_API_SUFFIX);
        request.append(String.valueOf(version));

        this.request = request;
    }

    public PublicApi(GetRequest request, int version) {
        request.append(PUBLIC_API_SUFFIX);
        request.append(String.valueOf(version));

        this.request = request;
        this.version = version;
    }

    public Info getInfo() throws IOException {
        this.request.append("info");

        String responseStr = request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, Info.class);
        } catch (JsonMappingException e) {
            JsonNode jsonNode = mapper.readTree(responseStr);

            if (jsonNode.has("success") && jsonNode.get("success").intValue() == 0) {
                throw new UnsuccessException(
                        jsonNode.get("success").intValue(),
                        jsonNode.get("error").toString()
                );
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    public TickerExecutor getTicker(String pair) throws IOException {
        this.request
                .append("ticker")
                .append(pair);

        return new TickerExecutor();
    }

    public TickerExecutor getTicker(List<String> pairs) throws IOException {
        this.request
                .append("ticker")
                .append(String.join("-", pairs));

        return new TickerExecutor();
    }

    public DepthExecutor getDepth(String pair) throws IOException {
        this.request
                .append("depth")
                .append(pair);

        return new DepthExecutor();
    }

    public DepthExecutor getDepth(List<String> pairs) throws IOException {
        this.request
                .append("depth")
                .append(String.join("-", pairs));

        return new DepthExecutor();
    }

    public TradeExecutor getTrade(String pair) throws IOException {
        this.request
                .append("trades")
                .append(pair);

        return new TradeExecutor();
    }

    public TradeExecutor getTrade(List<String> pairs) throws IOException {
        this.request
                .append("trades")
                .append(String.join("-", pairs));

        return new TradeExecutor();
    }

    public interface Executor<T> {
        T execute() throws IOException;
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

            try {
                for (Iterator<String> it = responseJson.fieldNames(); it.hasNext(); ) {
                    String pairName = it.next();
                    Ticker ticker = mapper.readValue(responseJson.get(pairName).toString(), Ticker.class);

                    result.put(pairName, ticker);
                }

                return result;
            } catch (JsonMappingException e) {
                JsonNode jsonNode = mapper.readTree(responseStr);

                if (jsonNode.has("success") && jsonNode.get("success").intValue() == 0) {
                    throw new UnsuccessException(
                            jsonNode.get("success").intValue(),
                            jsonNode.get("error").toString()
                    );
                } else {
                    throw new RuntimeException(e);
                }
            }
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

            try {
                for (Iterator<String> it = responseJson.fieldNames(); it.hasNext(); ) {
                    String pairName = it.next();
                    Depth depth = mapper.readValue(responseJson.get(pairName).toString(), Depth.class);

                    result.put(pairName, depth);
                }

                return result;
            } catch (JsonMappingException e) {
                JsonNode jsonNode = mapper.readTree(responseStr);

                if (jsonNode.has("success") && jsonNode.get("success").intValue() == 0) {
                    throw new UnsuccessException(
                            jsonNode.get("success").intValue(),
                            jsonNode.get("error").toString()
                    );
                } else {
                    throw new RuntimeException(e);
                }
            }
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

            try {
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
            } catch (JsonMappingException e) {
                JsonNode jsonNode = mapper.readTree(responseStr);

                if (jsonNode.has("success") && jsonNode.get("success").intValue() == 0) {
                    throw new UnsuccessException(
                            jsonNode.get("success").intValue(),
                            jsonNode.get("error").toString()
                    );
                } else {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
