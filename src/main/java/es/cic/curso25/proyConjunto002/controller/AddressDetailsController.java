package es.cic.curso25.proyConjunto002.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso25.proyConjunto002.model.AddressDetails;
import es.cic.curso25.proyConjunto002.service.AddressDetailsService;

@RestController
@RequestMapping("/address-details")
public class AddressDetailsController {

    @Autowired
    private AddressDetailsService addressDetailsService;

    @PostMapping
    public AddressDetails createAddressDetails(@RequestBody AddressDetails addressDetails) {
        return addressDetailsService.createAddressDetails(addressDetails);
    }

    @GetMapping
    public List<AddressDetails> getAllAddressDetails() {
        return addressDetailsService.getAllAddressDetails();
    }

    @PutMapping
    public AddressDetails updateAddressDetails(@RequestBody AddressDetails addressDetails) {
        return addressDetailsService.updateAddressDetails(addressDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteAddressDetails(@PathVariable Long id) {
        addressDetailsService.deleteAddressDetails(id);
    }
}
