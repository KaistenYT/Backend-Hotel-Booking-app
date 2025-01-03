package ks.dev.hotel_book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ks.dev.hotel_book.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String role);


    boolean existsByName(String role);
}