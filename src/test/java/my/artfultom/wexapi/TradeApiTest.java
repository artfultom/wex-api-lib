package my.artfultom.wexapi;

import my.artfultom.wexapi.exception.ReadOnlyException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * -Dkey= -Dsecret=
 */
public class TradeApiTest {

    private String key = System.getProperty("key");
    private String secret = System.getProperty("secret");

    @Before
    public void setup() {
        Assert.assertNotNull("You must set -Dkey parameter!", key);
        Assert.assertNotNull("You must set -Dsecret parameter!", secret);

        try {
            new WexClient("https://wex.nz").tradeApi();

            Assert.fail("Expected an ReadOnlyException to be thrown");
        } catch (ReadOnlyException e) {
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void getInfo() throws Exception {
        WexClient client = new WexClient("https://wex.nz", key, secret);

        System.out.println(client.tradeApi().getInfo());
    }

}