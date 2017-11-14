package my.artfultom.wexapi;

import my.artfultom.wexapi.dto.Depth;
import my.artfultom.wexapi.dto.Info;
import my.artfultom.wexapi.dto.Ticker;
import my.artfultom.wexapi.dto.Trade;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class PublicApiTest {

    private static final String BASE_URL = "https://wex.nz/api";
    private static final String DEFAULT_PAIR = "btc_usd";
    private static final List<String> DEFAULT_PAIRS = Arrays.asList("btc_usd", "ltc_btc");

    @Test
    public void getInfo() throws Exception {
        Info info1 = new WexClient(BASE_URL).publicApi().getInfo();

        assertNotNull(info1);
        assertNotNull(info1.getPairs());

        Info info2 = new WexClient(BASE_URL).publicApi(3).getInfo();

        assertNotNull(info2);
        assertNotNull(info2.getPairs());
    }

    @Test
    public void getTicker() throws Exception {
        Map<String, Ticker> tickerMap1 = new WexClient(BASE_URL).publicApi().getTicker(DEFAULT_PAIR);

        assertNotNull(tickerMap1);
        assertTrue(tickerMap1.size() == 1);
        assertNotNull(tickerMap1.get(DEFAULT_PAIR).getLast());

        Map<String, Ticker> tickerMap2 = new WexClient(BASE_URL).publicApi().getTicker(DEFAULT_PAIRS);

        assertNotNull(tickerMap2);
        assertTrue(tickerMap2.size() == 2);
        assertNotNull(tickerMap2.get(DEFAULT_PAIR).getLast());
        assertNotNull(tickerMap2.get("ltc_btc").getLast());
    }

    @Test
    public void getDepth() throws Exception {
        Map<String, Depth> depthMap1 = new WexClient(BASE_URL).publicApi().getDepth(DEFAULT_PAIR);

        assertNotNull(depthMap1);
        assertTrue(depthMap1.size() == 1);
        assertNotNull(depthMap1.get(DEFAULT_PAIR));
        assertNotNull(depthMap1.get(DEFAULT_PAIR).getAsks());
        assertNotNull(depthMap1.get(DEFAULT_PAIR).getBids());

        Map<String, Depth> depthMap2 = new WexClient(BASE_URL).publicApi().getDepth(DEFAULT_PAIRS);

        assertNotNull(depthMap2);
        assertTrue(depthMap2.size() == 2);
        assertNotNull(depthMap2.get("btc_usd"));
        assertNotNull(depthMap2.get("btc_usd").getAsks());
        assertNotNull(depthMap2.get("btc_usd").getBids());
        assertNotNull(depthMap2.get("ltc_btc"));
        assertNotNull(depthMap2.get("ltc_btc").getAsks());
        assertNotNull(depthMap2.get("ltc_btc").getBids());
    }

    @Test
    public void getTrade() throws Exception {
        Map<String, List<Trade>> trades1 = new WexClient(BASE_URL).publicApi().getTrade(DEFAULT_PAIR);

        assertNotNull(trades1);
        assertTrue(trades1.size() == 1);
        assertNotNull(trades1.get(DEFAULT_PAIR));
        assertNotNull(trades1.get(DEFAULT_PAIR).get(0));
        assertNotNull(trades1.get(DEFAULT_PAIR).get(0).getPrice());

        Map<String, List<Trade>> trades2 = new WexClient(BASE_URL).publicApi().getTrade(DEFAULT_PAIRS);

        assertNotNull(trades2);
        assertTrue(trades2.size() == 2);
        assertNotNull(trades2.get("btc_usd"));
        assertNotNull(trades2.get("btc_usd").get(0));
        assertNotNull(trades2.get("btc_usd").get(0).getPrice());
        assertNotNull(trades2.get("ltc_btc"));
        assertNotNull(trades2.get("ltc_btc").get(0));
        assertNotNull(trades2.get("ltc_btc").get(0).getPrice());
    }

}