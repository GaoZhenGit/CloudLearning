package com.codetend.service.consumer.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
    String INPUT1 = "consumer1";
    @Input(MySink.INPUT1)
    SubscribableChannel output1();

    String INPUT2 = "consumer2";
    @Input(MySink.INPUT2)
    SubscribableChannel output2();

    String INPUT3 = "consumer3";
    @Input(MySink.INPUT3)
    SubscribableChannel output3();
}
