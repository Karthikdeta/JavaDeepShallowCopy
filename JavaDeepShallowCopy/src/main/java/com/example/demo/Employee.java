package com.example.demo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Employee implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    public Employee clone() throws CloneNotSupportedException {
        Employee emp = (Employee) super.clone();
        emp.setAddress(emp.getAddress()
            .clone());
        return emp;
    }

    public Employee(Employee emp) {
        this(emp.getId(), emp.getName(), emp.getOrganization(), new Address(emp.getAddress()));
    }

    private int id;
    private String name;
    private String organization;
    private Address address;

}
