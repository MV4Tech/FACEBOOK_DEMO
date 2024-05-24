package com.example.facebook.facebook.demo.service;

import com.example.facebook.facebook.demo.dto.AddressDto;
import com.example.facebook.facebook.demo.model.Address;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AddressService {
    public void save(Address address);

    AddressDto addAddress(Address address, Authentication authentication);

    List<AddressDto> getAddressesByUserId(Authentication authentication);

    AddressDto updateAddress(Address address, Long id, Authentication authentication);

    AddressDto deleteAddress(Long id);

    void addAddressPage(Authentication authentication, Address address);
}
