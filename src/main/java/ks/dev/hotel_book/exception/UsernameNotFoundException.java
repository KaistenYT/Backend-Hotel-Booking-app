package ks.dev.hotel_book.exception;

public class UsernameNotFoundException  extends RuntimeException{

    public UsernameNotFoundException (String message){
        super(message);
    }

}
