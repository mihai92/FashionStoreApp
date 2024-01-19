package com.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FashionStoreApplicationTests {

    @Test
    void testStringName() {
        String stringName = "aaa";

        assertEquals("aaa", stringName);
    }

}
