package org.example.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderException extends Exception{

    public OrderException(String message){
        super(message);
    }
}
