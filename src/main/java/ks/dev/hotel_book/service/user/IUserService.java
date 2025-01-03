package ks.dev.hotel_book.service.user;

import java.util.List;

import ks.dev.hotel_book.model.User;

public interface IUserService {

    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);

}
