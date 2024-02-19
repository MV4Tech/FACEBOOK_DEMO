package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.AddressDto;
import com.example.facebook.facebook.demo.model.Address;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AddressService {
    public void save(Address address);

    void addAddress(Address address, Authentication authentication);

    List<AddressDto> getAddressesByUserId(Authentication authentication);

    void updateAddress(Address address, Long id);

    void deleteAddress(Long id);

    void addAddressPage(Authentication authentication, Address address);
}
