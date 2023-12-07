package com.example.itog.Controller;

import com.example.itog.model.Address;
import com.example.itog.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/address")

public class AddressController {

    private final AddressRepository addressRepository;


    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping
    public String listAddresses(Model model) {
        List<Address> addresses = addressRepository.findAll();
        model.addAttribute("addresses", addresses);
        return "address/list";
    }

    @GetMapping("/add")
    public String showAddAddressForm(Model model) {
        model.addAttribute("address", new Address());
        return "address/add";
    }

    @PostMapping("/add")
    public String addAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "address/add";
        }

        addressRepository.save(address);
        return "redirect:/address";
    }

    @GetMapping("/edit/{id}")
    public String showEditAddressForm(@PathVariable("id") Long id, Model model) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid address Id:" + id));

        model.addAttribute("address", address);
        return "address/edit";
    }

    @PostMapping("/edit/{id}")
    public String editAddress(@PathVariable("id") Long id, @Valid @ModelAttribute("address") Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            address.setId(id);
            return "address/edit";
        }

        addressRepository.save(address);
        return "redirect:/address";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid address Id:" + id));

        addressRepository.delete(address);
        return "redirect:/address";
    }
}
