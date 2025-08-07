package com.projetoRestaurante.Restaurante.controller;

import com.projetoRestaurante.Restaurante.entity.Address;
import com.projetoRestaurante.Restaurante.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Address> createAddress(
            @RequestHeader("X-Customer-Id") UUID customerId,
            @RequestBody Address address
    ) {
        return ResponseEntity.ok(addressService.criarNovoEndereco(customerId, address));
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Address>> getCustomerAddresses(
            @RequestHeader("X-Customer-Id") UUID customerId
    ) {
        return ResponseEntity.ok(addressService.getEnderecoPorId(customerId));
    }

    @PatchMapping("/{addressId}/default")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Address> setDefaultAddress(
            @RequestHeader("X-Customer-Id") UUID customerId,
            @PathVariable UUID addressId
    ) {
        return ResponseEntity.ok(addressService.setEnderecoPadrao(customerId, addressId));
    }


}