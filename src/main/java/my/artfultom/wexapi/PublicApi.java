package my.artfultom.wexapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.artfultom.wexapi.dto.Info;
import my.artfultom.wexapi.dto.Ticker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        Map<String, Ticker> result = new HashMap<>();

        this.request.append("ticker");
        this.request.append(pair);
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

    public Map<String, Ticker> getTicker(List<String> pairs) throws IOException {
        Map<String, Ticker> result = new HashMap<>();

        this.request.append("ticker");
        this.request.append(String.join("-", pairs));
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


    public void getDepth() {
    }

    public void getTrade() {
    }
}
