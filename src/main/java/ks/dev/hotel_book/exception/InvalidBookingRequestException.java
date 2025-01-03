package ks.dev.hotel_book.exception;

public class InvalidBookingRequestException extends RuntimeException {

    public InvalidBookingRequestException(String message){
        super(message);
    }

}
