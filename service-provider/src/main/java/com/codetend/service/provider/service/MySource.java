package com.codetend.service.provider.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MySource {
    String OUTPUT1 = "produce1";
    @Output(MySource.OUTPUT1)
    MessageChannel output1();

    String OUTPUT2 = "produce2";
    @Output(MySource.OUTPUT2)
    MessageChannel output2();

    String OUTPUT3 = "produce3";
    @Output(MySource.OUTPUT3)
    MessageChannel output3();
}

