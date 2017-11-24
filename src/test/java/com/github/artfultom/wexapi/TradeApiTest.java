package com.github.artfultom.wexapi;

import com.github.artfultom.wexapi.exception.ReadOnlyException;
import com.github.artfultom.wexapi.request.AuthorizedPostRequest;
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
                            "Wrong request:",
                            invocation.getArgument(0), "https://wex.nz/tapi"
                    );

                    return httpPost;
                });

        // TODO check body

        PowerMockito.mockStatic(EntityUtils.class);
        PowerMockito.when(EntityUtils.toString(Mockito.any())).thenReturn("{\"success\":1,\"return\":{}}");

        WexClient client = new WexClient("https://wex.nz", key, secret);

        client.tradeApi().getInfo();
    }

}