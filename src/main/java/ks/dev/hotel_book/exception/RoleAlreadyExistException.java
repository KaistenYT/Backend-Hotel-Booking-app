package ks.dev.hotel_book.exception;

public class RoleAlreadyExistException  extends RuntimeException{

    public RoleAlreadyExistException(String message){
    super(message);
}
}
