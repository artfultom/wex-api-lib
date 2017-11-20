package my.artfultom.wexapi;

import my.artfultom.wexapi.request.GetRequest;
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
import java.util.Arrays;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(value = {WexClient.class, GetRequest.class, HttpClients.class, EntityUtils.class})
public class PublicApiTest {

    private static final String BASE_URL = "https://wex.nz";
    private static final String DEFAULT_PAIR = "btc_usd";
    private static final List<String> DEFAULT_PAIRS = Arrays.asList("btc_usd", "ltc_btc");

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
                "https://wex.nz/api/3/info",
                "https://wex.nz/api/99/info"
        );

        HttpGet httpGet = Mockito.mock(HttpGet.class);

        PowerMockito
                .whenNew(HttpGet.class).withArguments(Mockito.anyString())
                .thenAnswer(new Answer<HttpGet>() {
                    private int counter;

                    @Override
                    public HttpGet answer(InvocationOnMock invocation) throws Throwable {
                        Assert.assertEquals(
                                "Wrong request #" + counter,
                                invocation.getArgument(0), answers.get(counter)
                        );

                        counter++;

                        return httpGet;
                    }
                });

        PowerMockito.mockStatic(EntityUtils.class);
        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn("{\"pairs\":{\"btc_usd\":{}}}");

        new WexClient(BASE_URL).publicApi().getInfo();
        new WexClient(BASE_URL).publicApi(99).getInfo();
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
                    public HttpGet answer(InvocationOnMock invocation) throws Throwable {
                        Assert.assertEquals(
                                "Wrong request #" + counter,
                                invocation.getArgument(0), answers.get(counter)
                        );

                        counter++;

                        return httpGet;
                    }
                });

        PowerMockito.mockStatic(EntityUtils.class);
        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn("{\"btc_usd\":{}}");

        new WexClient(BASE_URL).publicApi().getTicker(DEFAULT_PAIR).execute();
        new WexClient(BASE_URL).publicApi().getTicker(DEFAULT_PAIR).ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getTicker(DEFAULT_PAIRS).execute();
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
                    public HttpGet answer(InvocationOnMock invocation) throws Throwable {
                        Assert.assertEquals(
                                "Wrong request #" + counter,
                                invocation.getArgument(0), answers.get(counter)
                        );

                        counter++;

                        return httpGet;
                    }
                });

        PowerMockito.mockStatic(EntityUtils.class);
        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn("{\"btc_usd\":{\"asks\":[],\"bids\":[]}}");

        new WexClient(BASE_URL).publicApi().getDepth(DEFAULT_PAIR).execute();
        new WexClient(BASE_URL).publicApi().getDepth(DEFAULT_PAIR).ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getDepth(DEFAULT_PAIR).setLimit(1).execute();
        new WexClient(BASE_URL).publicApi().getDepth(DEFAULT_PAIR).setLimit(1).ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getDepth(DEFAULT_PAIRS).execute();
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
                    public HttpGet answer(InvocationOnMock invocation) throws Throwable {
                        Assert.assertEquals(
                                "Wrong request #" + counter,
                                invocation.getArgument(0), answers.get(counter)
                        );

                        counter++;

                        return httpGet;
                    }
                });

        PowerMockito.mockStatic(EntityUtils.class);
        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn("{\"btc_usd\":[{}]}");

        new WexClient(BASE_URL).publicApi().getTrade(DEFAULT_PAIR).execute();
        new WexClient(BASE_URL).publicApi().getTrade(DEFAULT_PAIR).ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getTrade(DEFAULT_PAIR).setLimit(1).execute();
        new WexClient(BASE_URL).publicApi().getTrade(DEFAULT_PAIR).setLimit(1).ignoreInvalid().execute();
        new WexClient(BASE_URL).publicApi().getTrade(DEFAULT_PAIRS).execute();
    }

}