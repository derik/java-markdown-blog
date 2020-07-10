package com.deriklima.blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class AdminControllerTest extends BaseControllerTest {

  @Test
  @WithMockUser
  void shouldReturnAdminPage() throws Exception {
    mockMvc.perform(get("/admin"))
        .andExpect(redirectedUrl("/admin/blog/list"))
        .andExpect(status().isFound());
  }

}