package com.github.artfultom.wexapi;

import com.github.artfultom.wexapi.exception.ReadOnlyException;
import com.github.artfultom.wexapi.request.AuthorizedPostRequest;
import com.github.artfultom.wexapi.tradeapi.dto.GetInfo;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * -Dkey= -Dsecret=
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.net.ssl.*", "javax.crypto.*"})
@PrepareForTest(value = {WexClient.class, AuthorizedPostRequest.class, HttpClients.class, EntityUtils.class})
public class TradeApiTest {

    private String key = System.getProperty("key");
    private String secret = System.getProperty("secret");

    @Before
    public void setup() throws IOException {
        Assert.assertNotNull("You must set -Dkey parameter!", key);
        Assert.assertNotNull("You must set -Dsecret parameter!", secret);

        try {
            new WexClient("https://wex.nz").tradeApi();

            Assert.fail("Expected an ReadOnlyException to be thrown");
        } catch (ReadOnlyException e) {
            Assert.assertNotNull(e);
        }

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
        HttpPost httpPost = Mockito.mock(HttpPost.class);

        PowerMockito
                .whenNew(HttpPost.class).withArguments(Mockito.anyString())
                .thenAnswer((Answer<HttpPost>) invocation -> {
                    Assert.assertEquals(
                            "Wrong request!",
                            invocation.getArgument(0), "https://wex.nz/tapi"
                    );

                    return httpPost;
                });

        PowerMockito.mockStatic(EntityUtils.class);
        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn("" +
                "{" +
                "\"success\":1," +
                "\"return\":" +
                "{" +
                "\"funds\":{\"usd\":0.1}," +
                "\"rights\":{\"info\":1,\"trade\":1,\"withdraw\":0}," +
                "\"open_orders\":0," +
                "\"server_time\":1518789451" +
                "}" +
                "}");

        WexClient client = new WexClient("https://wex.nz", key, secret);

        GetInfo info = client.tradeApi().getInfo();

        Assert.assertNotNull(info);
        Assert.assertEquals(info.getSuccess(), 1);
        Assert.assertNull(info.getError());

        Assert.assertNotNull(info.getReturnValue());
        Assert.assertNotNull(info.getReturnValue().getFunds());
        Assert.assertTrue(info.getReturnValue().getFunds().size() == 1);
        Assert.assertNotNull(info.getReturnValue().getFunds().get("usd"));
        Assert.assertEquals(info.getReturnValue().getFunds().get("usd"), BigDecimal.valueOf(0.1));

        Assert.assertNotNull(info.getReturnValue().getRights());
        Assert.assertTrue(info.getReturnValue().getRights().size() == 3);
        Assert.assertTrue(info.getReturnValue().getRights().get("info") == 1);
        Assert.assertTrue(info.getReturnValue().getRights().get("trade") == 1);
        Assert.assertTrue(info.getReturnValue().getRights().get("withdraw") == 0);

        Assert.assertEquals(info.getReturnValue().getOpenOrders(), 0);

        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(1518789451L * 1000),
                ZoneId.systemDefault()
        ); // TODO zoneId
        Assert.assertEquals(info.getReturnValue().getServerTime(), dateTime);
    }
}