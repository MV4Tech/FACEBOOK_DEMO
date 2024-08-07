package com.example.facebook.facebook.demo.service.impl;

import com.example.facebook.facebook.demo.dto.AddressDto;
import com.example.facebook.facebook.demo.exception.AddressNotFoundException;
import com.example.facebook.facebook.demo.exception.UserNotFoundException;
import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.model.Page;
import com.example.facebook.facebook.demo.model.User;
import com.example.facebook.facebook.demo.repository.AddressRepository;
import com.example.facebook.facebook.demo.service.AddressService;
import com.example.facebook.facebook.demo.service.JwtService;
import com.example.facebook.facebook.demo.service.PageService;
import com.example.facebook.facebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
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
    private final PageService pageService;

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public AddressDto addAddress(Address address, Authentication authentication) {
        Address newAddress = new Address();
        Long id = userService.findUserIdByAuthentication(authentication);
        Optional<User> optionalUser = userService.findById(id);
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setMunicipality(address.getMunicipality());
        newAddress.setUser(optionalUser.get());
        addressRepository.save(newAddress);
        logger.info("Address added successfully to user - " + optionalUser.get().getEmail());
        return new AddressDto(newAddress.getId(), newAddress.getCountry(), newAddress.getMunicipality(), newAddress.getCity());
    }


    @Override
    public List<AddressDto> getAddressesByUserId(Authentication authentication) {
        Long id = userService.findUserIdByAuthentication(authentication);
        List<Address> addresses = addressRepository.findAllByUserId(id);
        if (!(addresses.size() > 0)) {
            throw new AddressNotFoundException("Address not found");
        }
        List<AddressDto> addressDtos = addresses.stream().map(address -> {
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
    public AddressDto updateAddress(Address address, Long id, Authentication authentication) {
        Long userId = userService.findUserIdByAuthentication(authentication);
        Optional<User> optionalUser = userService.findById(userId);
        List<Long> addressIds = optionalUser.get().getAddresses().stream().map(Address::getId).collect(Collectors.toList());
        logger.info("Address ID - " + id);
        logger.info("Address IDs - " + addressIds);
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            throw new AddressNotFoundException("Address not found");
        }
        // Check if the address ID to be updated belongs to the user
        if (!addressIds.contains(id)) {
            throw new AddressNotFoundException("Address not found or does not belong to the user");
        }

        Address updatedAddress = optionalAddress.get();
        updatedAddress.setCity(address.getCity());
        updatedAddress.setCountry(address.getCountry());
        updatedAddress.setMunicipality(address.getMunicipality());
        addressRepository.save(updatedAddress);
        logger.info("Address updated successfully to user - " + optionalAddress.get().getUser().getEmail());
        return new AddressDto(updatedAddress.getId(), updatedAddress.getCountry(), updatedAddress.getMunicipality(), updatedAddress.getCity());
    }

    @Override
    public AddressDto deleteAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent()) {
            throw new AddressNotFoundException("Address not found");
        }
        addressRepository.deleteById(id);
        logger.info("Address deleted successfully to user - " + optionalAddress.get().getUser().getEmail());
        return new AddressDto(optionalAddress.get().getId(), optionalAddress.get().getCountry(), optionalAddress.get().getMunicipality(), optionalAddress.get().getCity());
    }

    @Override
    public void addAddressPage(Authentication authentication, Address address) {
        Address newAddress = new Address();
        Long pageId = pageService.findPageIdByAuthentication(authentication);
        Page optionalPage = pageService.getPageById(pageId);
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setMunicipality(address.getMunicipality());
        newAddress.setPage(optionalPage);
        addressRepository.save(newAddress);
        logger.info("Address added successfully to page - " + address.getPage().getId());
    }
}
