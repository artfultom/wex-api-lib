package com.github.artfultom.wexapi.pushapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.artfultom.wexapi.pushapi.dto.PushDepth;
import com.github.artfultom.wexapi.pushapi.dto.PushTrade;
import com.github.artfultom.wexapi.util.PushTradeType;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PushApi {

    private String cluster = "eu";

    private String key = "ee987526a24ba107824c";

    private Pusher pusher;

    public PushApi() {
        pusher = new Pusher(this.key, new PusherOptions().setCluster(this.cluster));
        pusher.connect();
    }

    public PushApi(String cluster, String key) {
        this.cluster = cluster;
        this.key = key;

        pusher = new Pusher(this.key, new PusherOptions().setCluster(this.cluster));
        pusher.connect();
    }

    public PushApi subscribeToDepth(String pair, Consumer<PushDepth> consumer) {
        Channel channel = pusher.subscribe(pair + ".depth");

        channel.bind("depth", (channelName, eventName, data) -> {
            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode jsonNode = mapper.readTree(data);

                PushDepth pushDepth = new PushDepth();

                if (jsonNode.has("ask")) {
                    List<PushDepth.Empty> askEmpties = new ArrayList<>();
                    jsonNode.get("ask").forEach(ask ->
                            askEmpties.add(new PushDepth.Empty(
                                    new BigDecimal(ask.get(0).asText()),
                                    new BigDecimal(ask.get(1).asText())
                            )));

                    pushDepth.setAsk(askEmpties);
                }

                if (jsonNode.has("bid")) {
                    List<PushDepth.Empty> bidEmpties = new ArrayList<>();
                    jsonNode.get("bid").forEach(bid ->
                            bidEmpties.add(new PushDepth.Empty(
                                    new BigDecimal(bid.get(0).asText()),
                                    new BigDecimal(bid.get(1).asText())
                            )));

                    pushDepth.setBid(bidEmpties);
                }

                consumer.accept(pushDepth);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return this;
    }

    public PushApi subscribeToTrade(String pair, Consumer<List<PushTrade>> consumer) {
        Channel channel = pusher.subscribe(pair + ".trades");

        channel.bind("trades", (channelName, eventName, data) -> {
            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode jsonNode = mapper.readTree(data);

                List<PushTrade> result = new ArrayList<>();

                jsonNode.forEach(trade ->
                        result.add(new PushTrade(
                                PushTradeType.valueOf(trade.get(0).asText().toUpperCase()),
                                new BigDecimal(trade.get(1).asText()),
                                new BigDecimal(trade.get(2).asText())
                        )));

                consumer.accept(result);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return this;
    }
}
