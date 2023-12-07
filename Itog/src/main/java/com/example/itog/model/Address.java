package com.example.itog.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Улица не может быть пустой")
    @Size(max = 100, message = "Улица не должна превышать 100 символов")
    private String street;

    @NotBlank(message = "Город не может быть пустым")
    @Size(max = 50, message = "Город не должен превышать 50 символов")
    private String city;

   // @Pattern(regexp = "\\d{5}", message = "Почтовый индекс должен содержать 5 цифр")
    // private String postalCode;



    @OneToMany(mappedBy = "address")
    private List<Ord> ords;


    public Address() {}

    public Address(Long id, String street, String city, String postalCode, List<Ord> ords) {
        this.id = id;
        this.street = street;
        this.city = city;
       // this.postalCode = postalCode;
        this.ords = ords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /*public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }*/

    public List<Ord> getOrds() {
        return ords;
    }

    public void setOrds(List<Ord> ords) {
        this.ords = ords;
    }
}

