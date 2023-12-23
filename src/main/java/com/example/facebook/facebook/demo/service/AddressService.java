package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.AddressDto;
import com.example.facebook.facebook.demo.model.Address;

import java.util.List;

public interface AddressService {
    public void save(Address address);

    void addAddress(Address address, Long id);

    List<AddressDto> getAddressesByUserId(Long id);

    void updateAddress(Address address, Long id);

    void deleteAddress(Long id);
}
