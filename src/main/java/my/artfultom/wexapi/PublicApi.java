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
    private GetRequest request = null;

    public PublicApi(GetRequest request) {
        request.append(String.valueOf(version));
        this.request = request;
    }

    public PublicApi(GetRequest request, int version) {
        this.version = version;

        request.append(String.valueOf(version));
        this.request = request;
    }

    public Info getInfo() throws IOException {
        this.request.append("info");
        String responseStr = request.execute();

        return new ObjectMapper().readValue(responseStr, Info.class);
    }

    public Map<String, Ticker> getTicker(String pair) throws IOException {
        this.request.append("ticker");
        this.request.append(pair);
        String responseStr = request.execute();

        return this.parseTicker(responseStr);
    }

    public Map<String, Ticker> getTicker(List<String> pairs) throws IOException {
        this.request.append("ticker");
        this.request.append(String.join("-", pairs));
        String responseStr = request.execute();

        return this.parseTicker(responseStr);
    }

    private Map<String, Ticker> parseTicker(String responseStr) throws IOException {
        Map<String, Ticker> result = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJson = mapper.readTree(responseStr);

        for (Iterator<String> it = responseJson.fieldNames(); it.hasNext(); ) {
            String pairName = it.next();
            Ticker ticker = mapper.readValue(responseJson.get(pairName).toString(), Ticker.class);

            result.put(pairName, ticker);
        }

        return result;
    }

    public Map<String, Depth> getDepth(String pair) throws IOException {
        this.request.append("depth");
        this.request.append(pair);
        String responseStr = request.execute();

        return this.parseDepth(responseStr);
    }

    public Map<String, Depth> getDepth(List<String> pairs) throws IOException {
        this.request.append("depth");
        this.request.append(String.join("-", pairs));
        String responseStr = request.execute();

        return this.parseDepth(responseStr);
    }

    private Map<String, Depth> parseDepth(String responseStr) throws IOException {
        Map<String, Depth> result = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseJson = mapper.readTree(responseStr);

        for (Iterator<String> it = responseJson.fieldNames(); it.hasNext(); ) {
            String pairName = it.next();
            Depth depth = mapper.readValue(responseJson.get(pairName).toString(), Depth.class);

            result.put(pairName, depth);
        }

        return result;
    }

    public Map<String, List<Trade>> getTrade(String pair) throws IOException {
        this.request.append("trades");
        this.request.append(pair);
        String responseStr = request.execute();

        return this.parseTrade(responseStr);
    }

    public Map<String, List<Trade>> getTrade(List<String> pairs) throws IOException {
        this.request.append("trades");
        this.request.append(String.join("-", pairs));
        String responseStr = request.execute();

        return this.parseTrade(responseStr);
    }

    private Map<String, List<Trade>> parseTrade(String responseStr) throws IOException {
        Map<String, List<Trade>> result = new HashMap<>();

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
