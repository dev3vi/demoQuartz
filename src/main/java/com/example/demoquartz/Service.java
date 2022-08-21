package com.example.demoquartz;

import org.springframework.stereotype.Component;

@Component
public class Service {

    public void inject(){
        System.out.println("Inject Success!");
    }
}
