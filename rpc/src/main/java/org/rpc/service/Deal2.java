package org.rpc.service;

import org.springframework.stereotype.Service;

@Service
public class Deal2 implements DealService {
    @Override
    public void print() {
        System.out.println("deal2");
    }

    @Override
    public Object source() {
        return "2";
    }
}
