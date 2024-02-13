package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.dto.AddressDto;
import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    // set User address controller
    @PostMapping("/add-address/{id}")
    public ResponseEntity<Void> addAddress(@RequestBody @Valid Address address, @PathVariable Long id) {
           addressService.addAddress(address, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // get all addresses from user controller
    @GetMapping("/get-addresses/{id}")
    public ResponseEntity<List<AddressDto>> getAddressesByUserId(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddressesByUserId(id));
    }

    // update address controller
    @PutMapping("/update-address/{id}")
    public ResponseEntity<Void> updateAddress(@RequestBody @Valid Address address,@PathVariable Long id){
        addressService.updateAddress(address,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // delete address controller
    @DeleteMapping("/delete-address/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return  ResponseEntity.noContent().build();
    }


    // set Page address controller
    @PostMapping("/add-address-page")
    public ResponseEntity<Void> addAddressPage(@RequestBody @Valid Address address) {
        addressService.addAddressPage(address);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // update Page address controller
    @PutMapping("/update-address-page/{id}")
    public ResponseEntity<Void> updateAddressPage(@RequestBody @Valid Address address,@PathVariable Long id){
        addressService.updateAddress(address,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // delete Page address controller
    @DeleteMapping("/delete-address-page/{id}")
    public ResponseEntity<Void> deleteAddressPage(@PathVariable Long id){
        addressService.deleteAddress(id);
        return  ResponseEntity.noContent().build();
    }

}
