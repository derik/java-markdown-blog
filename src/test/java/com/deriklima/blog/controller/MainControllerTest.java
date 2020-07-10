package com.deriklima.blog.controller;

import static com.deriklima.blog.controller.ResourcePath.ABOUT_PAGE;
import static com.deriklima.blog.controller.ResourcePath.BLOG_POST_PAGE;
import static com.deriklima.blog.controller.ResourcePath.CONTACT_PAGE;
import static com.deriklima.blog.controller.ResourcePath.INDEX_PAGE;
import static com.deriklima.blog.controller.ResourcePath.PRIVACY_PAGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainControllerTest extends BaseControllerTest {

  @Test
  void shouldReturnIndexPage() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("posts"))
        .andExpect(view().name(INDEX_PAGE));
  }

  @Test
  void shouldReturnBlogPostPage() throws Exception {
    mockMvc.perform(get("/blog/blog-post-01"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("post"))
        .andExpect(model().attributeExists("postHtml"))
        .andExpect(view().name(BLOG_POST_PAGE));
  }

  @Test
  void shouldReturnAboutPage() throws Exception {
    mockMvc.perform(get("/about"))
        .andExpect(status().isOk())
        .andExpect(view().name(ABOUT_PAGE));
  }

  @Test
  void shouldReturnContactPage() throws Exception {
    mockMvc.perform(get("/contact"))
        .andExpect(status().isOk())
        .andExpect(view().name(CONTACT_PAGE));
  }

  @Test
  void shouldReturnPrivacyPage() throws Exception {
    mockMvc.perform(get("/privacy"))
        .andExpect(status().isOk())
        .andExpect(view().name(PRIVACY_PAGE));
  }

}