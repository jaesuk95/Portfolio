package com.portfolio.api;

import com.portfolio.factory.WithUser;
import com.portfolio.utils.JsonUtils;
import com.portfolio.web.payload.LoginPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.Cookie;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(value = {"classpath:database/mockData.sql"})
public class AuthApiTest {

    @Autowired
    private MockMvc mvc;

//    @Test
//    @WithUser(value = "admin")
//    void login_test() throws Exception{
//
//        LoginPayload payload = new LoginPayload();
//
//        mvc.perform(post("/api/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .cookie(new Cookie("_uuid", "abc"))
//                .content(JsonUtils.toJson(payload))
//        ).andExpect(status().isOk()).andDo(print()).andReturn();
//    }

}
