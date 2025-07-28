package es.cic.curso25.proyConjunto002.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.curso25.proyConjunto002.model.AddressDetails;
import es.cic.curso25.proyConjunto002.repository.AddressDetailsRepository;

@Service
public class AddressDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressDetailsService.class);

    @Autowired
    private AddressDetailsRepository addressDetailsRepository;

    // crear dirección
    public AddressDetails createAddressDetails(AddressDetails addressDetails) {
        LOGGER.info("Dirección creada");
        return addressDetailsRepository.save(addressDetails);
    }

    // listar todas las direcciones
    public List<AddressDetails> getAllAddressDetails() {
        LOGGER.info("Leer todas las direcciones");
        return addressDetailsRepository.findAll();
    }

    // modificar dirección
    public AddressDetails updateAddressDetails(AddressDetails addressDetails) {
        LOGGER.info("Dirección modificada");
        return addressDetailsRepository.save(addressDetails);
    }

    // borrar dirección
    public void deleteAddressDetails(Long id) {
        LOGGER.info("Dirección eliminada");
        addressDetailsRepository.deleteById(id);
    }
}