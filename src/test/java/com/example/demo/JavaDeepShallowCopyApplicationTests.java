package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JavaDeepShallowCopyApplicationTests {

    Employee employee1;

    Address address;

    @BeforeEach
    void setup() {
        address = Address.builder()
            .addressLine1("147 N. Brent Street")
            .city("Ventura")
            .state("CA")
            .country("USA")
            .postalCode("93003-2854")
            .build();

        employee1 = Employee.builder()
            .id(1)
            .name("Karthik")
            .organization("UPS")
            .address(address)
            .build();
    }

    @Test
    void shallowCopyWithAssignmentOperator() {
        Employee employee2 = employee1;
        assertTrue(employee1 == employee2);
        assertEquals(employee1, employee2);

        employee1.getAddress()
            .setState("NC");

        assertTrue(employee1 == employee2);
        assertEquals(employee1, employee2);

        employee2.setAddress(Address.builder()
            .build());

        assertTrue(employee1 == employee2);
        assertTrue(employee1.getAddress() == employee2.getAddress());
        assertEquals(employee1, employee2);
    }

    @Test
    void deepCopyUsingConstructorCopy() {
        Employee employee2 = new Employee(employee1);
        assertFalse(employee1 == employee2);
        assertEquals(employee1, employee2);

        employee1.setName("Test");
        employee1.getAddress()
            .setState("NC");

        assertFalse(employee1 == employee2);
        assertNotEquals(employee1.getName(), employee2.getName());
        assertNotEquals(employee1, employee2);
        assertFalse(employee1.getAddress() == employee2.getAddress());
    }

    @Test
    void deepCopyUsingCloneMethod() throws CloneNotSupportedException {
        Employee employee2 = employee1.clone();
        assertFalse(employee1 == employee2);
        assertEquals(employee1, employee2);

        employee1.setName("Test");
        employee1.getAddress()
            .setState("NC");

        assertFalse(employee1 == employee2);
        assertNotEquals(employee1.getName(), employee2.getName());
        assertNotEquals(employee1, employee2);
        assertFalse(employee1.getAddress() == employee2.getAddress());
    }

    @Test
    void deepCopyUsingSerializationUtils() {
        Employee employee2 = SerializationUtils.clone(employee1);
        assertFalse(employee1 == employee2);
        assertEquals(employee1, employee2);

        employee1.setName("Test");
        employee1.getAddress()
            .setState("NC");

        assertFalse(employee1 == employee2);
        assertNotEquals(employee1.getName(), employee2.getName());
        assertNotEquals(employee1, employee2);
        assertFalse(employee1.getAddress() == employee2.getAddress());
    }
}
