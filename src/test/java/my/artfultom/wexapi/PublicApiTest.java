package my.artfultom.wexapi;

import my.artfultom.wexapi.dto.Info;
import my.artfultom.wexapi.dto.Ticker;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class PublicApiTest {

    public static final String BASE_URL = "https://wex.nz/api";

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
        Map<String, Ticker> tickerMap1 = new WexClient(BASE_URL).publicApi().getTicker("btc_usd");

        assertNotNull(tickerMap1);
        assertTrue(tickerMap1.size() == 1);

        Map<String, Ticker> tickerMap2 = new WexClient(BASE_URL).publicApi().getTicker(Arrays.asList("btc_usd", "ltc_btc"));

        assertNotNull(tickerMap2);
        assertTrue(tickerMap2.size() == 2);
    }

    @Test
    public void getTicker1() throws Exception {
    }

    @Test
    public void getDepth() throws Exception {
    }

    @Test
    public void getTrade() throws Exception {
    }

}