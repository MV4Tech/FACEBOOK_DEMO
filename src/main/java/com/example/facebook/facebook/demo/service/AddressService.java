package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.model.Address;

import java.util.List;

public interface AddressService {
    public void save(Address address);

    Address addAddress(Address address, Long id);

    List<Address> getAddressesByUserId(Long id);

    Address updateAddress(Address address, Long id);
}
