package ru.knastnt.tryrestconfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.knastnt.tryrestconfig.dto.FooDto;
import ru.knastnt.tryrestconfig.entities.Foo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class) - видимо для обычного JUnit

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

    // При обращении к api без авторизации - 302 редирект
    @Test
    @WithAnonymousUser(setupBefore = TestExecutionEvent.TEST_METHOD)  //если не указать setupBefore, то этот юзер распространяется на соседние методы теста', х.з.
    public void api_nologin_302() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound()) //code 302
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/login"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos/1"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound()) //code 302
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/login"));
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/foos/1", new FooDto(1L, "testname")))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound()) //code 302
                .andExpect(MockMvcResultMatchers.redirectedUrlPattern("**/login"));
    }

    // При обращении к api юзера без права PERM_MANAGE_USERS - 403 доступ запрещен
    @Test
    @WithMockUser(authorities = { "FAKE_AUTHORITY" }, setupBefore = TestExecutionEvent.TEST_METHOD)  //если не указать setupBefore, то этот юзер распространяется на соседние методы теста', х.з.
    public void api_no_permission_403() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos/1"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/foos/1", new FooDto(1L, "testname")))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    // При обращении к api юзера с правом SCOPE_read - только чтение
    @Test
    @WithMockUser(authorities = { "SCOPE_read" }, setupBefore = TestExecutionEvent.TEST_METHOD)  //если не указать setupBefore, то этот юзер распространяется на соседние методы теста', х.з.
    public void api_only_read() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos/1"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos/-1"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/foos/1", new FooDto(1L, "testname")))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    // При обращении к api юзера с правами SCOPE_read и SCOPE_write -  чтение и изменение
    @Test
    @WithMockUser(authorities = { "SCOPE_read", "SCOPE_write" }, setupBefore = TestExecutionEvent.TEST_METHOD)  //если не указать setupBefore, то этот юзер распространяется на соседние методы теста', х.з.
    public void api_read_and_write() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos/1"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/foos/-1"))
//                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());



        FooDto fooDto = new FooDto(1L, "testname");
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/foos/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .characterEncoding("UTF-8")
                    .content(convertToJson(fooDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().json(convertToJson(fooDto)));
    }



    private String convertToJson(FooDto foo) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(foo);
        return requestJson;
    }


}
