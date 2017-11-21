package my.artfultom.wexapi.tradeapi;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.artfultom.wexapi.exception.UnsuccessException;
import my.artfultom.wexapi.request.AuthorizedPostRequest;
import my.artfultom.wexapi.tradeapi.dto.GetInfo;
import my.artfultom.wexapi.util.OrderType;
import my.artfultom.wexapi.util.SortOrder;

import java.io.IOException;
import java.math.BigDecimal;

public class TradeApi {

    private static final String TRADE_API_PREFIX = "tapi";

    private AuthorizedPostRequest request;

    public TradeApi(AuthorizedPostRequest request) {
        request.append(TRADE_API_PREFIX);

        this.request = request;
    }

    public GetInfo getInfo() throws IOException {
        this.request.addParameter("method", "getInfo");

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, GetInfo.class);
        } catch (JsonMappingException e) {
            JsonNode jsonNode = mapper.readTree(responseStr);

            if (jsonNode.has("success") && jsonNode.get("success").intValue() == 0) {
                throw new UnsuccessException(
                        jsonNode.get("success").intValue(),
                        jsonNode.get("error").toString()
                );
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    public String trade(String pair, OrderType type, BigDecimal rate, BigDecimal amount) throws IOException {
        this.request.addParameter("method", "Trade");
        this.request.addParameter("pair", pair);
        this.request.addParameter("type", type.toString());
        this.request.addParameter("rate", rate.toString());
        this.request.addParameter("amount", amount.toString());

        return this.request.execute();
    }

    public String activeOrders(String pair) throws IOException {
        this.request.addParameter("method", "ActiveOrders");
        this.request.addParameter("pair", pair);

        return this.request.execute();
    }

    public String orderInfo(Long orderId) throws IOException {
        this.request.addParameter("method", "OrderInfo");
        this.request.addParameter("order_id", orderId.toString());

        return this.request.execute();
    }

    public String cancelOrder(Long orderId) throws IOException {
        this.request.addParameter("method", "CancelOrder");
        this.request.addParameter("order_id", orderId.toString());

        return this.request.execute();
    }

    public String tradeHistory(
            Long from,
            Integer count,
            Long fromId,
            Long endId,
            SortOrder sortOrder,
            Long since,
            Long end,
            String pair
    ) throws IOException {
        this.request.addParameter("method", "TradeHistory");
        this.request.addParameter("from", from.toString());
        this.request.addParameter("count", count.toString());
        this.request.addParameter("from_id", fromId.toString());
        this.request.addParameter("end_id", endId.toString());
        this.request.addParameter("order", sortOrder.toString());
        this.request.addParameter("since", since.toString());
        this.request.addParameter("end", end.toString());
        this.request.addParameter("pair", pair);

        return this.request.execute();
    }

    public String transHistory(
            Long from,
            Integer count,
            Long fromId,
            Long endId,
            SortOrder sortOrder,
            Long since,
            Long end
    ) throws IOException {
        this.request.addParameter("method", "TransHistory");
        this.request.addParameter("from", from.toString());
        this.request.addParameter("count", count.toString());
        this.request.addParameter("from_id", fromId.toString());
        this.request.addParameter("end_id", endId.toString());
        this.request.addParameter("order", sortOrder.toString());
        this.request.addParameter("since", since.toString());
        this.request.addParameter("end", end.toString());

        return this.request.execute();
    }

    public String coinDepositAddress(String coinName) throws IOException {
        this.request.addParameter("method", "CoinDepositAddress");
        this.request.addParameter("coinName", coinName);

        return this.request.execute();
    }

    public String withdrawCoin(String coinName, BigDecimal amount, String address) throws IOException {
        this.request.addParameter("method", "WithdrawCoin");
        this.request.addParameter("coinName", coinName);
        this.request.addParameter("amount", amount.toString());
        this.request.addParameter("address", address);

        return this.request.execute();
    }

    public String createCoupon(String currency, BigDecimal amount, String receiver) throws IOException {
        this.request.addParameter("method", "CreateCoupon");
        this.request.addParameter("currency", currency);
        this.request.addParameter("amount", amount.toString());
        this.request.addParameter("receiver", receiver);

        return this.request.execute();
    }

    public String redeemCoupon(String coupon) throws IOException {
        this.request.addParameter("method", "RedeemCoupon");
        this.request.addParameter("coupon", coupon);

        return this.request.execute();
    }
}
