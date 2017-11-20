package my.artfultom.wexapi.request;

import my.artfultom.wexapi.WexClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AuthorizedPostRequest extends GenericRequest {

    public AuthorizedPostRequest(WexClient client) {
        super(client);

        this.client = client;
    }

    @Override
    public String execute() throws IOException {
        String result;

        int nonce = this.client.getNonce();

        for (int i = 0; i < 10; i++) {
            this.addParameter("nonce", Integer.toString(nonce));

            HttpPost httpPost = new HttpPost(this.requestUrl);

            httpPost.setEntity(new UrlEncodedFormEntity(new ArrayList<>(this.parameters.values()), "UTF-8"));

            httpPost.addHeader("Key", this.client.getKey());

            String postDataStr = String.join(
                    "&",
                    this.parameters.values().stream()
                            .map(item -> item.getName() + "=" + item.getValue())
                            .collect(Collectors.toList())
            );

            String sign = this.getHmacSHA512(postDataStr, this.client.getSecret());

            httpPost.addHeader("Sign", sign);

            HttpResponse response = this.client.getHttpClient().execute(httpPost);
            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity);

            if (result.contains("invalid nonce parameter")) {
                nonce = Integer.valueOf(result.split("you should send:")[1].replaceAll("[\\D]", ""));
            } else {
                nonce++;

                this.client.setNonce(nonce);

                return result;
            }
        }

        throw new RuntimeException("Invalid nonce parameter: " + nonce + "!");
    }

    private String getHmacSHA512(String str, String secret) {
        try {
            Mac macInst = Mac.getInstance("HmacSHA512");
            macInst.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA512"));

            return DatatypeConverter.printHexBinary((macInst.doFinal(str.getBytes("UTF-8")))).toLowerCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
