package com.example.facebook.facebook.demo.controller;

import com.example.facebook.facebook.demo.model.Address;
import com.example.facebook.facebook.demo.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Address> addAddress(@RequestBody @Valid Address address, @PathVariable Long id) {
        return ResponseEntity.ok(addressService.addAddress(address, id));
    }

    // get all addresses from user controller
    @GetMapping("/get-addresses/{id}")
    public ResponseEntity<List<Address>> getAddressesByUserId(@PathVariable Long id){
        return ResponseEntity.ok(addressService.getAddressesByUserId(id));
    }

    // update address controller
    @PutMapping("/update-address/{id}")
    public ResponseEntity<Address> updateAddress(@RequestBody @Valid Address address,@PathVariable Long id){
        return ResponseEntity.ok(addressService.updateAddress(address,id));
    }

}
