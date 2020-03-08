package org.rpc.service;

import org.springframework.stereotype.Service;

@Service
public class Deal1 implements DealService {
    @Override
    public void print() {
        System.out.println("deal1");
    }

    @Override
    public Object source() {
        return "1";
    }
}
