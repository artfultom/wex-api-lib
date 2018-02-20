package com.github.artfultom.wexapi;

import com.github.artfultom.wexapi.publicapi.dto.Depth;
import com.github.artfultom.wexapi.publicapi.dto.Info;
import com.github.artfultom.wexapi.publicapi.dto.Ticker;
import com.github.artfultom.wexapi.publicapi.dto.Trade;
import com.github.artfultom.wexapi.request.GetRequest;
import com.github.artfultom.wexapi.util.TradeType;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(value = {WexClient.class, GetRequest.class, HttpClients.class, EntityUtils.class})
public class PublicApiTest {

    private static final String BASE_URL = "https://wex.nz";

    @Before
    public void setup() throws IOException {
        HttpEntity httpEntity = Mockito.mock(HttpEntity.class);

        CloseableHttpResponse closeableHttpResponse = Mockito.mock(CloseableHttpResponse.class);
        Mockito.when(closeableHttpResponse.getEntity()).thenReturn(httpEntity);

        CloseableHttpClient httpClient = Mockito.mock(CloseableHttpClient.class);
        Mockito.when(httpClient.execute(Mockito.any())).thenReturn(closeableHttpResponse);

        PowerMockito.mockStatic(HttpClients.class);
        PowerMockito.when(HttpClients.createDefault()).thenReturn(httpClient);
    }

    @Test
    public void getInfo() throws Exception {
        List<String> answers = Arrays.asList(
                "https://wex.nz/api/3/info"
        );

        HttpGet httpGet = Mockito.mock(HttpGet.class);

        PowerMockito
                .whenNew(HttpGet.class).withArguments(Mockito.anyString())
                .thenAnswer(new Answer<HttpGet>() {
                    private int counter;

                    @Override
                    public HttpGet answer(InvocationOnMock invocation) {
                        Assert.assertEquals(
                                "Wrong request #" + counter,
                                invocation.getArgument(0), answers.get(counter)
                        );

                        counter++;

                        return httpGet;
                    }
                });

        PowerMockito.mockStatic(EntityUtils.class);

        String response = "" +
                "{\n" +
                "\"server_time\":1370814956,\n" +
                "\"pairs\":\n" +
                "{\n" +
                "\"btc_usd\":\n" +
                "{\n" +
                "\"decimal_places\":3,\n" +
                "\"min_price\":0.1,\n" +
                "\"max_price\":400,\n" +
                "\"min_amount\":0.01,\n" +
                "\"hidden\":0,\n" +
                "\"fee\":0.2\n" +
                "}\n" +
                "}\n" +
                "}";

        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn(response);

        Info info1 = new WexClient(BASE_URL).publicApi().getInfo();

        Assert.assertNotNull(info1.getServerTime());
        Assert.assertFalse(info1.getPairs().isEmpty());

        Info.Pair pair1 = info1.getPairs().get("btc_usd");
        Assert.assertNotNull(pair1);
        Assert.assertEquals(pair1.getDecimalPlaces(), 3);
        Assert.assertEquals(pair1.getMinPrice(), BigDecimal.valueOf(0.1));
        Assert.assertEquals(pair1.getMaxPrice(), BigDecimal.valueOf(400));
        Assert.assertEquals(pair1.getMinAmount(), BigDecimal.valueOf(0.01));
        Assert.assertEquals(pair1.getHidden(), 0);
        Assert.assertEquals(pair1.getFee(), BigDecimal.valueOf(0.2));
    }

    @Test
    public void getTicker() throws Exception {
        List<String> answers = Arrays.asList(
                "https://wex.nz/api/3/ticker/btc_usd",
                "https://wex.nz/api/3/ticker/btc_usd?ignore_invalid=true",
                "https://wex.nz/api/3/ticker/btc_usd-ltc_btc"
        );

        HttpGet httpGet = Mockito.mock(HttpGet.class);

        PowerMockito
                .whenNew(HttpGet.class).withArguments(Mockito.anyString())
                .thenAnswer(new Answer<HttpGet>() {
                    private int counter;

                    @Override
                    public HttpGet answer(InvocationOnMock invocation) {
                        Assert.assertEquals(
                                "Wrong request #" + counter,
                                invocation.getArgument(0), answers.get(counter)
                        );

                        counter++;

                        return httpGet;
                    }
                });

        PowerMockito.mockStatic(EntityUtils.class);

        String response = "" +
                "{\n" +
                "\"btc_usd\":\n" +
                "{\n" +
                "\"high\":109.88,\n" +
                "\"low\":91.14,\n" +
                "\"avg\":100.51,\n" +
                "\"vol\":1632898.2249,\n" +
                "\"vol_cur\":16541.51969,\n" +
                "\"last\":101.773,\n" +
                "\"buy\":101.9,\n" +
                "\"sell\":101.773,\n" +
                "\"updated\":1370816308\n" +
                "}\n" +
                "}";

        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn(response);

        Map<String, Ticker> tickers = new WexClient(BASE_URL).publicApi().getTicker("btc_usd").execute();

        Assert.assertNotNull(tickers);
        Assert.assertEquals(tickers.size(), 1);

        Ticker ticker = tickers.get("btc_usd");

        Assert.assertNotNull(ticker);
        Assert.assertEquals(ticker.getHigh(), BigDecimal.valueOf(109.88));
        Assert.assertEquals(ticker.getLow(), BigDecimal.valueOf(91.14));
        Assert.assertEquals(ticker.getAvg(), BigDecimal.valueOf(100.51));
        Assert.assertEquals(ticker.getVol(), BigDecimal.valueOf(1632898.2249));
        Assert.assertEquals(ticker.getVolCur(), BigDecimal.valueOf(16541.51969));
        Assert.assertEquals(ticker.getLast(), BigDecimal.valueOf(101.773));
        Assert.assertEquals(ticker.getBuy(), BigDecimal.valueOf(101.9));
        Assert.assertEquals(ticker.getSell(), BigDecimal.valueOf(101.773));
        Assert.assertEquals(ticker.getUpdated(), 1370816308);

        new WexClient(BASE_URL).publicApi().getTicker("btc_usd").ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getTicker(Arrays.asList("btc_usd", "ltc_btc")).execute();
    }

    @Test
    public void getDepth() throws Exception {
        List<String> answers = Arrays.asList(
                "https://wex.nz/api/3/depth/btc_usd",
                "https://wex.nz/api/3/depth/btc_usd?ignore_invalid=true",
                "https://wex.nz/api/3/depth/btc_usd?limit=1",
                "https://wex.nz/api/3/depth/btc_usd?limit=1&ignore_invalid=true",
                "https://wex.nz/api/3/depth/btc_usd-ltc_btc"
        );

        HttpGet httpGet = Mockito.mock(HttpGet.class);

        PowerMockito
                .whenNew(HttpGet.class).withArguments(Mockito.anyString())
                .thenAnswer(new Answer<HttpGet>() {
                    private int counter;

                    @Override
                    public HttpGet answer(InvocationOnMock invocation) {
                        Assert.assertEquals(
                                "Wrong request #" + counter,
                                invocation.getArgument(0), answers.get(counter)
                        );

                        counter++;

                        return httpGet;
                    }
                });

        PowerMockito.mockStatic(EntityUtils.class);

        String response = "" +
                "{\n" +
                "\"btc_usd\":\n" +
                "{\n" +
                "\"asks\":\n" +
                "[\n" +
                "[103.426,0.01],\n" +
                "[103.5,15],\n" +
                "[103.504,0.425],\n" +
                "[103.505,0.1]\n" +
                "],\n" +
                "\"bids\":\n" +
                "[\n" +
                "[103.2,2.48502251],\n" +
                "[103.082,0.46540304],\n" +
                "[102.91,0.99007913],\n" +
                "[102.83,0.07832332]\n" +
                "]\n" +
                "}\n" +
                "}";

        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn(response);

        Map<String, Depth> depths = new WexClient(BASE_URL).publicApi().getDepth("btc_usd").execute();

        Assert.assertNotNull(depths);

        Depth depth = depths.get("btc_usd");

        Assert.assertNotNull(depth);

        List<BigDecimal[]> asks = depth.getAsks();

        Assert.assertNotNull(asks);
        Assert.assertEquals(asks.size(), 4);
        Assert.assertEquals(asks.get(0)[0], BigDecimal.valueOf(103.426));
        Assert.assertEquals(asks.get(0)[1], BigDecimal.valueOf(0.01));
        Assert.assertEquals(asks.get(1)[0], BigDecimal.valueOf(103.5));
        Assert.assertEquals(asks.get(1)[1], BigDecimal.valueOf(15));
        Assert.assertEquals(asks.get(2)[0], BigDecimal.valueOf(103.504));
        Assert.assertEquals(asks.get(2)[1], BigDecimal.valueOf(0.425));
        Assert.assertEquals(asks.get(3)[0], BigDecimal.valueOf(103.505));
        Assert.assertEquals(asks.get(3)[1], BigDecimal.valueOf(0.1));

        List<BigDecimal[]> bids = depth.getBids();

        Assert.assertNotNull(bids);
        Assert.assertEquals(bids.size(), 4);
        Assert.assertEquals(bids.get(0)[0], BigDecimal.valueOf(103.2));
        Assert.assertEquals(bids.get(0)[1], BigDecimal.valueOf(2.48502251));
        Assert.assertEquals(bids.get(1)[0], BigDecimal.valueOf(103.082));
        Assert.assertEquals(bids.get(1)[1], BigDecimal.valueOf(0.46540304));
        Assert.assertEquals(bids.get(2)[0], BigDecimal.valueOf(102.91));
        Assert.assertEquals(bids.get(2)[1], BigDecimal.valueOf(0.99007913));
        Assert.assertEquals(bids.get(3)[0], BigDecimal.valueOf(102.83));
        Assert.assertEquals(bids.get(3)[1], BigDecimal.valueOf(0.07832332));

        new WexClient(BASE_URL).publicApi().getDepth("btc_usd").ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getDepth("btc_usd").setLimit(1).execute();
        new WexClient(BASE_URL).publicApi().getDepth("btc_usd").setLimit(1).ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getDepth(Arrays.asList("btc_usd", "ltc_btc")).execute();
    }

    @Test
    public void getTrade() throws Exception {
        List<String> answers = Arrays.asList(
                "https://wex.nz/api/3/trades/btc_usd",
                "https://wex.nz/api/3/trades/btc_usd?ignore_invalid=true",
                "https://wex.nz/api/3/trades/btc_usd?limit=1",
                "https://wex.nz/api/3/trades/btc_usd?limit=1&ignore_invalid=true",
                "https://wex.nz/api/3/trades/btc_usd-ltc_btc"
        );

        HttpGet httpGet = Mockito.mock(HttpGet.class);

        PowerMockito
                .whenNew(HttpGet.class).withArguments(Mockito.anyString())
                .thenAnswer(new Answer<HttpGet>() {
                    private int counter;

                    @Override
                    public HttpGet answer(InvocationOnMock invocation) {
                        Assert.assertEquals(
                                "Wrong request #" + counter,
                                invocation.getArgument(0), answers.get(counter)
                        );

                        counter++;

                        return httpGet;
                    }
                });

        PowerMockito.mockStatic(EntityUtils.class);

        String response = "{\n" +
                "\"btc_usd\":[\n" +
                "{\n" +
                "\"type\":\"ask\",\n" +
                "\"price\":103.6,\n" +
                "\"amount\":0.101,\n" +
                "\"tid\":4861261,\n" +
                "\"timestamp\":1370818007\n" +
                "},\n" +
                "{\n" +
                "\"type\":\"bid\",\n" +
                "\"price\":103.989,\n" +
                "\"amount\":1.51414,\n" +
                "\"tid\":4861254,\n" +
                "\"timestamp\":1370817960\n" +
                "}\n" +
                "]\n" +
                "}";

        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn(response);

        Map<String, List<Trade>> tradeMap = new WexClient(BASE_URL).publicApi().getTrade("btc_usd").execute();

        Assert.assertNotNull(tradeMap);

        List<Trade> trades = tradeMap.get("btc_usd");

        Assert.assertEquals(trades.size(), 2);
        Assert.assertEquals(trades.get(0).getTradeType(), TradeType.ASK);
        Assert.assertEquals(trades.get(0).getPrice(), BigDecimal.valueOf(103.6));
        Assert.assertEquals(trades.get(0).getAmount(), BigDecimal.valueOf(0.101));
        Assert.assertEquals(trades.get(0).getTid(), 4861261);
        Assert.assertEquals(trades.get(0).getTimestamp(), 1370818007);

        Assert.assertEquals(trades.get(1).getTradeType(), TradeType.BID);
        Assert.assertEquals(trades.get(1).getPrice(), BigDecimal.valueOf(103.989));
        Assert.assertEquals(trades.get(1).getAmount(), BigDecimal.valueOf(1.51414));
        Assert.assertEquals(trades.get(1).getTid(), 4861254);
        Assert.assertEquals(trades.get(1).getTimestamp(), 1370817960);

        new WexClient(BASE_URL).publicApi().getTrade("btc_usd").ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getTrade("btc_usd").setLimit(1).execute();
        new WexClient(BASE_URL).publicApi().getTrade("btc_usd").setLimit(1).ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getTrade(Arrays.asList("btc_usd", "ltc_btc")).execute();
    }

}