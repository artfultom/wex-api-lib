package my.artfultom.wexapi.tradeapi;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.artfultom.wexapi.request.AuthorizedPostRequest;
import my.artfultom.wexapi.tradeapi.dto.*;
import my.artfultom.wexapi.util.OrderType;
import my.artfultom.wexapi.util.SortOrder;

import java.io.IOException;
import java.math.BigDecimal;

public class TradeApi {

    private static final String TRADE_API_SUFFIX = "tapi";

    private AuthorizedPostRequest request;

    public TradeApi(AuthorizedPostRequest request) {
        request.append(TRADE_API_SUFFIX);

        this.request = request;
    }

    public GetInfo getInfo() throws IOException {
        this.request.addParameter("method", "getInfo");

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, GetInfo.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public Trade trade(String pair, OrderType type, BigDecimal rate, BigDecimal amount) throws IOException {
        this.request.addParameter("method", "Trade");
        this.request.addParameter("pair", pair);
        this.request.addParameter("type", type.toString());
        this.request.addParameter("rate", rate.toString());
        this.request.addParameter("amount", amount.toString());

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, Trade.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public ActiveOrders activeOrders(String pair) throws IOException {
        this.request.addParameter("method", "ActiveOrders");
        this.request.addParameter("pair", pair);

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, ActiveOrders.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderInfo orderInfo(Long orderId) throws IOException {
        this.request.addParameter("method", "OrderInfo");
        this.request.addParameter("order_id", orderId.toString());

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, OrderInfo.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public CancelOrder cancelOrder(Long orderId) throws IOException {
        this.request.addParameter("method", "CancelOrder");
        this.request.addParameter("order_id", orderId.toString());

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, CancelOrder.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public TradeHistory tradeHistory(
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

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, TradeHistory.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public TransactionsHistory transHistory(
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

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, TransactionsHistory.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public CoinDepositAddress coinDepositAddress(String coinName) throws IOException {
        this.request.addParameter("method", "CoinDepositAddress");
        this.request.addParameter("coinName", coinName);

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, CoinDepositAddress.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public WithdrawCoin withdrawCoin(String coinName, BigDecimal amount, String address) throws IOException {
        this.request.addParameter("method", "WithdrawCoin");
        this.request.addParameter("coinName", coinName);
        this.request.addParameter("amount", amount.toString());
        this.request.addParameter("address", address);

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, WithdrawCoin.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public CreateCoupon createCoupon(String currency, BigDecimal amount, String receiver) throws IOException {
        this.request.addParameter("method", "CreateCoupon");
        this.request.addParameter("currency", currency);
        this.request.addParameter("amount", amount.toString());
        this.request.addParameter("receiver", receiver);

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, CreateCoupon.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }

    public RedeemCoupon redeemCoupon(String coupon) throws IOException {
        this.request.addParameter("method", "RedeemCoupon");
        this.request.addParameter("coupon", coupon);

        String responseStr = this.request.execute();

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseStr, RedeemCoupon.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
    }
}
