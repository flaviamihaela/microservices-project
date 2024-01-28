//Define package
package com.programming;

//Imports
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = FetchServiceApplicationTest.class)
public class FetchServiceApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void contextLoads() {
        // Assert that the application context loads successfully
        assertNotNull(context, "Application context should not be null");
    }
}
