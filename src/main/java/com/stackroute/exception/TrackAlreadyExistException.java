package com.stackroute.exception;

public class TrackAlreadyExistException extends Exception{
    private String message;
    public TrackAlreadyExistException(String message){
        super(message);
        this.message=message;
    }
    public TrackAlreadyExistException(){

    }
}
