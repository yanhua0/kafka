package org.rpc.web;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Animals {
    private Cat cat;
    private List<Dog> dog;
}
