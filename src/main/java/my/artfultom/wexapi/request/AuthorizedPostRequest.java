package my.artfultom.wexapi.request;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizedPostRequest extends GenericRequest {

    private List<NameValuePair> postData = new ArrayList<>();

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
        HttpPost httpPost = new HttpPost(this.url);

        httpPost.setEntity(new UrlEncodedFormEntity(postData, "UTF-8"));

        httpPost.addHeader("Key", this.key);

        String postDataStr = String.join(
                "&",
                postData.stream().map(item -> item.getName() + "=" + item.getValue()).collect(Collectors.toList())
        );

        String sign = this.getHmacSHA512(postDataStr, this.secret);

        httpPost.addHeader("Sign", sign);

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        return EntityUtils.toString(entity);

        // {"success":0,"error":"invalid nonce parameter; on key:212372980, you sent:'', you should send:212372981"}
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
