package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.AddressDto;
import com.example.facebook.facebook.demo.exception.AddressNotFoundException;
import com.example.facebook.facebook.demo.exception.UserNotFoundException;
import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.AddressRepository;
import com.example.facebook.facebook.demo.service.AddressService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void addAddress(Address address, Long id) {
        Address newAddress = new Address();
        Optional<User> optionalUser = userService.findById(id);
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setMunicipality(address.getMunicipality());
        newAddress.setUser(optionalUser.get());
        addressRepository.save(newAddress);
        logger.info("Address added successfully to user - " + optionalUser.get().getEmail());
    }

    @Override
    public List<AddressDto> getAddressesByUserId(Long id) {
        List<Address> addresses = addressRepository.findAllByUserId(id);
        if(!(addresses.size() > 0)){
            throw new AddressNotFoundException("Address not found");
        }
        List<AddressDto> addressDtos = addresses.stream().map(address-> {
            AddressDto addressDto = new AddressDto();
            addressDto.setId(address.getId());
            addressDto.setCountry(address.getCountry());
            addressDto.setMunicipality(address.getMunicipality());
            addressDto.setCity(address.getCity());
            return addressDto;
        }).collect(Collectors.toList());
        logger.info("Addresses fetched successfully for user");
        return addressDtos;
    }

    @Override
    public void updateAddress(Address address, Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(!optionalAddress.isPresent()){
            throw new AddressNotFoundException("Address not found");
        }
        Address updatedAddress = optionalAddress.get();
        updatedAddress.setCity(address.getCity());
        updatedAddress.setCountry(address.getCountry());
        updatedAddress.setMunicipality(address.getMunicipality());
        addressRepository.save(updatedAddress);
        logger.info("Address updated successfully to user - " + optionalAddress.get().getUser().getEmail() + "or page - " + optionalAddress.get().getPage().getId());
        }

    @Override
    public void deleteAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(!optionalAddress.isPresent()){
            throw new AddressNotFoundException("Address not found");
        }
        addressRepository.deleteById(id);
        logger.info("Address deleted successfully to user - " + optionalAddress.get().getUser().getEmail() + " or page - " + optionalAddress.get().getPage().getId());
    }

    @Override
    public void addAddressPage(Address address) {
        addressRepository.save(address);
        logger.info("Address added successfully to page - " + address.getPage().getId());
    }
}
