package com.example.contracts;

import com.example.contracts.Person;
import com.example.contracts.PersonController;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ContractVerifierBase {

    @LocalServerPort
    int port;

    @MockBean
    private PersonController personController;

    @Test
    public void contextLoads() {
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:" + this.port;

        Person person = new Person("1", "Giel", "Reynders", 99);

        Mockito.when(personController.createPerson(ArgumentMatchers.any()))
                .thenReturn(person);
    }

}
