package ks.dev.hotel_book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ks.dev.hotel_book.model.User;

import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    void deleteByEmail(String email);

   Optional<User> findByEmail(String email);
}