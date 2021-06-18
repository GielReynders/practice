package com.example.contracts.controller;

import com.example.contracts.Person;
import com.example.contracts.PersonController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PersonRestControllerTest {

    private static final String ID = "2";
    private static final String FIRST_NAME = "Giel";
    private static final String LAST_NAME = "Reynders";
    private static final int AGE = 29;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonController personController;

    @BeforeEach
    void before() {

        Person person = new Person("2", "Giel", "Reynders", 29);

        Mockito.when(personController.createPerson(ArgumentMatchers.any()))
                .thenReturn(person);
    }

    @Test
    public void applicationContext() {
    }

    @Test
    public void getPersons() throws Exception {
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    public void createAPerson() throws Exception {
        Person person = new Person(ID, FIRST_NAME, LAST_NAME, AGE);

        mockMvc.perform(put("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.age", is(AGE)))

        ;
    }
}
