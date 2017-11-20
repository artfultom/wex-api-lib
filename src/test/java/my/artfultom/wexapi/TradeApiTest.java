package my.artfultom.wexapi;

import org.junit.Test;

/**
 * -Dkey= -Dsecret=
 */
public class TradeApiTest {

    @Test
    public void getInfo() throws Exception {
        String key = System.getProperty("key");
        String secret = System.getProperty("secret");

        WexClient client = new WexClient("https://wex.nz", key, secret);

        System.out.println(client.tradeApi().getInfo());
    }

}