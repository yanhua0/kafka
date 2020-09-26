package org.rpc.web;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Dog {
    private String name;
    private String breed;
    private int count;
    private boolean twoFeet;
}
