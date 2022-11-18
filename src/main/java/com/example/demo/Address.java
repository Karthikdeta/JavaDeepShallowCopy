package com.example.demo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Address implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    public Address(Address address) {
        this(address.getAddressLine1(), address.getAddressLine2(), address.getCity(), address.getState(), address.getCountry(), address.getPostalCode());
    }

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String postalCode;

}
