package com.codetend.udp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleForwardItem {
    private String userFragment;
    private Addr dest;
    private Addr source;
}
