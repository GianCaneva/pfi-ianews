package com.uade.ainews.newsGeneration.excepcion;

public class CustomException extends Exception{

    public CustomException(String message){
        super(message);
    }

    public CustomException (String message, Exception ex){
        super(message, ex);
    }
}

