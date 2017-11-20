package my.artfultom.wexapi.request;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

public class AuthorizedPostRequest extends GenericRequest {

    private String key;

    private String secret;

    private Integer nonce;

    public AuthorizedPostRequest(CloseableHttpClient httpClient, String url, String key, String secret, Integer nonce) {
        super(httpClient, url);

        this.key = key;
        this.secret = secret;
        this.nonce = nonce;
    }

    @Override
    public String execute() throws IOException {
        String result;

        for (int i = 0; i < 10; i++) {
            if (this.nonce != null) {
                this.addParameter("nonce", this.nonce.toString());
            }

            HttpPost httpPost = new HttpPost(this.url);

            httpPost.setEntity(new UrlEncodedFormEntity(this.parameters, "UTF-8"));

            httpPost.addHeader("Key", this.key);

            String postDataStr = String.join(
                    "&",
                    this.parameters.stream().map(item -> item.getName() + "=" + item.getValue()).collect(Collectors.toList())
            );

            String sign = this.getHmacSHA512(postDataStr, this.secret);

            httpPost.addHeader("Sign", sign);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity);

            if (result.contains("invalid nonce parameter")) {
                this.nonce = Integer.valueOf(result.split("you should send:")[1].replaceAll("[\\D]", ""));
            } else {
                this.nonce++;

                return result;
            }
        }

        throw new RuntimeException("Invalid nonce parameter: " + this.nonce + "!");
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
