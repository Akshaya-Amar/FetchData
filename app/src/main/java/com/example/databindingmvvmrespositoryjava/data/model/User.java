package com.example.databindingmvvmrespositoryjava.data.model;

public class User {
    private String name;
    private String email;
    private Address address;

    // to avoid getters, use public fields instead of private
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
}
