package com.sda.final_project_wro27;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Address {

    private String city;
    private String street;
    @Enumerated(EnumType.STRING)
    private Countries country;
    private String zipCode;

    public static Address apply(RegistrationDto registrationDto) {
        Address address = new Address();
        address.city = registrationDto.getCity();
        address.street = registrationDto.getStreet();
        address.country = Countries.getCountries(registrationDto.getCountry());
        address.zipCode = registrationDto.getZipCode();
        return address;
    }
}
