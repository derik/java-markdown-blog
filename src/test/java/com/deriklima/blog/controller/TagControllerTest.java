package com.deriklima.blog.controller;

import static com.deriklima.blog.controller.ResourcePath.ADMIN_TAG_LIST_PAGE;
import static com.deriklima.blog.controller.ResourcePath.ADMIN_TAG_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class TagControllerTest extends BaseControllerTest {

  @Test
  @WithMockUser
  void shouldReturnTagListPage() throws Exception {
    mockMvc.perform(get(ADMIN_TAG_PATH))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("tags"))
        .andExpect(model().attributeExists("newTag"))
        .andExpect(view().name(ADMIN_TAG_LIST_PAGE));
  }

}