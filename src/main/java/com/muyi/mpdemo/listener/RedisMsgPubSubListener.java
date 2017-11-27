package com.muyi.mpdemo.listener;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPubSub;

/**
 * @Author: muyi
 * @Date: Created in 10:59 2017/11/24
 * @Description: Redis message Pub/Sub listener
 */
@Slf4j
public class RedisMsgPubSubListener extends JedisPubSub {

    private OnMessageHandler handler;

    public RedisMsgPubSubListener(OnMessageHandler handler){
        this.handler = handler;
    }
    public interface OnMessageHandler{
        void onMessage(String channel, String message);
    }
    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

    @Override
    public void unsubscribe(String... channels) {
        super.unsubscribe(channels);
    }

    @Override
    public void subscribe(String... channels) {
        super.subscribe(channels);
    }

    @Override
    public void psubscribe(String... patterns) {
        super.psubscribe(patterns);
    }

    @Override
    public void punsubscribe() {
        super.punsubscribe();
    }

    @Override
    public void punsubscribe(String... patterns) {
        super.punsubscribe(patterns);
    }

    @Override
    public void onMessage(String channel, String message) {
        log.info("channel:" + channel + " received message :" + message);
        handler.onMessage(channel,message);
        //this.unsubscribe();
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        log.info("channel:" + channel + " has been subscribed:" + subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        log.info("channel:" + channel + " has been unsubscribed:" + subscribedChannels);
    }




}
