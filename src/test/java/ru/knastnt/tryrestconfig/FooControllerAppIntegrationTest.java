package ru.knastnt.tryrestconfig;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

/**
 * Мы можем загрузить только определенный фрагмент конфигурации приложения или смоделировать весь
 * процесс запуска контекста. Например, мы можем использовать аннотацию @SpringBootTest, если хотим,
 * чтобы весь контекст создавался без запуска сервера
 */
@SpringBootTest

/**
 * мы можем добавить @AutoConfigureMockMvc, чтобы внедрить экземпляр MockMvc и отправлять HTTP-запросы
 */
@AutoConfigureMockMvc
public class FooControllerAppIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenTestApp_thenEmptyResponse() throws Exception {
        /*this.mockMvc.perform(get("/foos")
                .andExpect(status().isOk())
                .andExpect(...);*/
    }

}
