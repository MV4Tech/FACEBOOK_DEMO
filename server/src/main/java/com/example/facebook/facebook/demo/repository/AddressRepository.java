package com.example.facebook.facebook.demo.repository;

import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.user.id = :userId")
    List<Address> findAllByUserId(Long userId);
}
