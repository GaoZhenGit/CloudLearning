package com.codetend.udp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    @GetMapping("/info")
    public Addr info() {
        Addr addr = new Addr();
        addr.setIp("0.0.0.0");
        addr.setPort(0);
        return addr;
    }
}
