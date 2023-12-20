package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.repository.AddressRepository;
import com.example.facebook.facebook.demo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }
}
