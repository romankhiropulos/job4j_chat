package ru.job4j.chat.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.job4j.chat.Job4jChatApplication;

@SpringBootTest(classes = Job4jChatApplication.class)
@AutoConfigureMockMvc
@Sql({"/schema-test.sql"})
class PersonControllerTest {

    @Test
    @Sql({"/schema-test.sql"})
    public void whenAskRegThanSaveNewUser() throws Exception {

    }
}