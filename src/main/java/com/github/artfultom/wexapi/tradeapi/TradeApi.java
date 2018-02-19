package com.github.artfultom.wexapi.tradeapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.artfultom.wexapi.request.AuthorizedPostRequest;
import com.github.artfultom.wexapi.tradeapi.dto.*;
import com.github.artfultom.wexapi.util.OrderType;
import com.github.artfultom.wexapi.util.SortOrder;

import java.io.IOException;
import java.math.BigDecimal;

public class TradeApi {

    private static final String TRADE_API_SUFFIX = "tapi";

    private AuthorizedPostRequest request;

    public TradeApi(AuthorizedPostRequest request) {
        request.append(TRADE_API_SUFFIX);

        this.request = request;
    }

    public GetInfo getInfo() {
        this.request.addParameter("method", "getInfo");

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, GetInfo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Trade trade(String pair, OrderType type, BigDecimal rate, BigDecimal amount) {
        this.request.addParameter("method", "Trade");
        this.request.addParameter("pair", pair);
        this.request.addParameter("type", type.name().toLowerCase());
        this.request.addParameter("rate", rate.toString());
        this.request.addParameter("amount", amount.toString());

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, Trade.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ActiveOrders activeOrders(String pair) {
        this.request.addParameter("method", "ActiveOrders");
        this.request.addParameter("pair", pair);

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, ActiveOrders.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderInfo orderInfo(Long orderId) {
        this.request.addParameter("method", "OrderInfo");
        this.request.addParameter("order_id", orderId.toString());

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, OrderInfo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CancelOrder cancelOrder(Long orderId) {
        this.request.addParameter("method", "CancelOrder");
        this.request.addParameter("order_id", orderId.toString());

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, CancelOrder.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TradeHistoryExecutor tradeHistory() {
        this.request.addParameter("method", "TradeHistory");

        return new TradeHistoryExecutor();
    }

    public TransactionsHistoryExecutor transHistory() {
        this.request.addParameter("method", "TransHistory");

        return new TransactionsHistoryExecutor();
    }

    public CoinDepositAddress coinDepositAddress(String coinName) {
        this.request.addParameter("method", "CoinDepositAddress");
        this.request.addParameter("coinName", coinName);

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, CoinDepositAddress.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WithdrawCoin withdrawCoin(String coinName, BigDecimal amount, String address) {
        this.request.addParameter("method", "WithdrawCoin");
        this.request.addParameter("coinName", coinName);
        this.request.addParameter("amount", amount.toString());
        this.request.addParameter("address", address);

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, WithdrawCoin.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CreateCoupon createCoupon(String currency, BigDecimal amount, String receiver) {
        this.request.addParameter("method", "CreateCoupon");
        this.request.addParameter("currency", currency);
        this.request.addParameter("amount", amount.toString());
        this.request.addParameter("receiver", receiver);

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, CreateCoupon.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RedeemCoupon redeemCoupon(String coupon) {
        this.request.addParameter("method", "RedeemCoupon");
        this.request.addParameter("coupon", coupon);

        try {
            String responseStr = this.request.execute();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(responseStr, RedeemCoupon.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public interface Executor<T> {
        T execute();
    }

    public class TradeHistoryExecutor implements Executor<TradeHistory> {

        public TradeHistoryExecutor from(Long offset) {
            request.addParameter("from", offset.toString());

            return this;
        }

        public TradeHistoryExecutor count(Integer limit) {
            request.addParameter("count", limit.toString());

            return this;
        }

        public TradeHistoryExecutor fromId(Long fromId) {
            request.addParameter("from_id", fromId.toString());

            return this;
        }

        public TradeHistoryExecutor endId(Long endId) {
            request.addParameter("end_id", endId.toString());

            return this;
        }

        public TradeHistoryExecutor order(SortOrder sortOrder) {
            request.addParameter("order", sortOrder.toString());

            return this;
        }

        public TradeHistoryExecutor since(Long unixTime) {
            request.addParameter("since", unixTime.toString());

            return this;
        }

        public TradeHistoryExecutor end(Long unixTime) {
            request.addParameter("end", unixTime.toString());

            return this;
        }

        public TradeHistoryExecutor pair(String pair) {
            request.addParameter("pair", pair);

            return this;
        }

        @Override
        public TradeHistory execute() {
            try {
                String responseStr = request.execute();

                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(responseStr, TradeHistory.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class TransactionsHistoryExecutor implements Executor<TransactionsHistory> {

        public TransactionsHistoryExecutor from(Long offset) {
            request.addParameter("from", offset.toString());

            return this;
        }

        public TransactionsHistoryExecutor count(Integer limit) {
            request.addParameter("count", limit.toString());

            return this;
        }

        public TransactionsHistoryExecutor fromId(Long fromId) {
            request.addParameter("from_id", fromId.toString());

            return this;
        }

        public TransactionsHistoryExecutor endId(Long endId) {
            request.addParameter("end_id", endId.toString());

            return this;
        }

        public TransactionsHistoryExecutor order(SortOrder sortOrder) {
            request.addParameter("order", sortOrder.toString());

            return this;
        }

        public TransactionsHistoryExecutor since(Long unixTime) {
            request.addParameter("since", unixTime.toString());

            return this;
        }

        public TransactionsHistoryExecutor end(Long unixTime) {
            request.addParameter("end", unixTime.toString());

            return this;
        }

        @Override
        public TransactionsHistory execute() {
            try {
                String responseStr = request.execute();

                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(responseStr, TransactionsHistory.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
