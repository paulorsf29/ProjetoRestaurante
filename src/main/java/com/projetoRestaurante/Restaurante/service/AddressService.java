package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.Address;
import com.projetoRestaurante.Restaurante.entity.User;
import com.projetoRestaurante.Restaurante.repository.AddressRepository;
import com.projetoRestaurante.Restaurante.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Transactional
    public Address criarNovoEndereco(UUID customerId, Address address){
        User cliente = userRepository.findById(customerId).orElseThrow(()-> new RuntimeException("cliente não encontrado"));
        address.setCustomer(cliente);

        if (address.isDefalt()){
            addressRepository.findByCustomerIdAndIsDefaltTrue(customerId).ifPresent(existente -> {
                existente.setDefalt(false);
                addressRepository.save(existente);
            });
        }
        return addressRepository.save(address);
    }

    public List<Address> getEnderecoPorId(UUID id){
        return addressRepository.findByCustomerId(id);
    }

    @Transactional
    public Address setEnderecoPadrao(UUID customerId, UUID addressId) {
        Address newDefault = addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        if (!newDefault.getCustomer().getId().equals(customerId)) {
            throw new RuntimeException("Endereço não pertence ao cliente");
        }

        // Desmarca o endereço padrão atual
        addressRepository.findByCustomerIdAndIsDefaltTrue(customerId)
                .ifPresent(existingDefault -> {
                    existingDefault.setDefalt(false);
                    addressRepository.save(existingDefault);
                });

        // Marca o novo como padrão
        newDefault.setDefalt(true);
        return addressRepository.save(newDefault);
    }


}
