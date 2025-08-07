package com.projetoRestaurante.Restaurante.service;

import com.projetoRestaurante.Restaurante.entity.Address;
import com.projetoRestaurante.Restaurante.entity.Area_delivery;
import com.projetoRestaurante.Restaurante.repository.AddressRepository;
import com.projetoRestaurante.Restaurante.repository.AreaDeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final AreaDeliveryRepository areaDeliveryRepository;
    private final AddressRepository addressRepository;

    public boolean deliveryDisponivel(double latitude, double longitude){
        return areaDeliveryRepository.findDeliveryAreaByCoordenates(latitude,longitude).isPresent();
    }

    public Integer calculoCustoDelivery(UUID addressId){
        Address address = addressRepository.findById(addressId).orElseThrow();
        Area_delivery areaDelivery = areaDeliveryRepository.findDeliveryAreaByCoordenates(
                address.getLatitude(),
                address.getLongitude()
        ).orElseThrow();
        return areaDelivery.getRecebimentoDelivery();
    }


}
