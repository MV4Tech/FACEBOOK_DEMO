package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.AddressDto;
import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    // set User address controller
    @PostMapping("/add-address")
    public ResponseEntity<AddressDto> addAddress(@RequestBody @Valid Address address,Authentication authentication) {
          AddressDto addressDto = addressService.addAddress(address, authentication);
        return ResponseEntity.ok(addressDto);
    }

    // get all addresses from user controller
    @GetMapping("/get-addresses")
    public ResponseEntity<List<AddressDto>> getAddressesByUserId(Authentication authentication){
        return ResponseEntity.ok(addressService.getAddressesByUserId(authentication));
    }

    // update address controller
    @PutMapping("/update-address/{id}")
    public ResponseEntity<AddressDto> updateAddress(@RequestBody @Valid Address address,@PathVariable Long id, Authentication authentication){
        AddressDto addressDto = addressService.updateAddress(address,id, authentication);
        return ResponseEntity.ok(addressDto);
    }

    // delete address controller
    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<AddressDto> deleteAddress(@PathVariable Long id){
        AddressDto addressDto = addressService.deleteAddress(id);
        return ResponseEntity.ok(addressDto);
    }




    // set Page address controller
    //TODO: add address to page POSTMAN WE
    @PostMapping("/add-address-page")
    public ResponseEntity<Void> addAddressPage(@RequestBody @Valid Address address, Authentication authentication){
        addressService.addAddressPage(authentication,address);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    // update Page address controller
    @PutMapping("/update-address-page/{id}")
    public ResponseEntity<Void> updateAddressPage(@RequestBody @Valid Address address,@PathVariable Long id, Authentication authentication){
        addressService.updateAddress(address,id, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // delete Page address controller
    @DeleteMapping("/delete-address-page/{id}")
    public ResponseEntity<Void> deleteAddressPage(@PathVariable Long id){
        addressService.deleteAddress(id);
        return  ResponseEntity.noContent().build();
    }

}
