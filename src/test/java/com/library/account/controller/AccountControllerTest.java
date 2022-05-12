package com.library.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.account.entity.Account;
import com.library.testcontainers.config.ContainerEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.library.account.factory.AccountCreator.adminWithoutdate;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest extends ContainerEnvironment {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private WebApplicationContext context;
    
    private ObjectMapper objectMapper;
    private Account account;
    
    
    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        
        objectMapper = new ObjectMapper();
        account = adminWithoutdate();
        // FIXME: 11.05.2022 Problem with parsing LocalDateTime into String made me create admin without createdAt -> lame
    }
    
    @Test
    @DisplayName("Unauthorized GET /api/accounts/ with returns 401 (unauthorized")
    void shouldReturnUnauthorizedStatusAfterGet() throws Exception {
        
        mvc.perform(get("/api/accounts")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status()
                        .isUnauthorized());
    }
    
    @Test
    @DisplayName("Authorized GET /api/accounts returns 202 (accepted")
    void shouldReturnAcceptedStatusWithAuthorizedAccountAfterGet() throws Exception {
        mvc.perform(get("/api/accounts")
                        .with(user(account.getLogin()).password(account.getPassword()))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isAccepted());
    }
    
    @Test
    @DisplayName("Authorized POST /api/accounts returns 201 (created)")
    void shouldReturnCreatedStatusWithAuthorizedAccountAfterPost() throws Exception {
        
        
        String json = objectMapper.writeValueAsString(account);
        
        mvc
                .perform(post("/api/accounts")
                        .with(user(account.getLogin()).password(account.getPassword()))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
    
    @Test
    @DisplayName("Authorized POST /api/accounts with not @Valid body returns 418 (handled Response")
    void shouldReturnImATeapotStatusWhenNoValidBodyAfterPost() throws Exception {
        
        final Account underTest = new Account();
        underTest.setLogin("undertestlogin");
        
        mvc
                .perform(post("/api/accounts")
                        .with(user(account.getLogin()).password(account.getPassword()))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(underTest.getLogin()))
                .andDo(print())
                .andExpect(status().isIAmATeapot())
                .andReturn();
    }
    
    @Test
    @DisplayName("Unathorized POST /api/accounts returns 418 (handled Response")
    void shouldReturnErrorStatusWithUnauthorizedAccountAfterPost() throws Exception {
        
        String json = objectMapper.writeValueAsString(account);
        
        mvc
                .perform(post("/api/accounts")
                        .with(anonymous())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
    
    @Test
    @DisplayName("Authorized DELETE /api/accounts/{id} returns 410 (gone)")
    void shouldReturnGoneStatusWithAuthorizedAccountAfterDeleteById() throws Exception {
        
        String json = objectMapper.writeValueAsString(account);

//        mvc
//                .perform(post("/api/accounts")
//                        .with(user(account.getLogin()).password(account.getPassword()))
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andDo(print());
//
//        String stringId = String.valueOf(account.getId());

//        mvc.
//                perform(delete("/api/accounts/" + stringId)
//                        .with(user(account.getLogin()).password(account.getPassword()))
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isGone())
//                .andReturn();
    }
    
}
