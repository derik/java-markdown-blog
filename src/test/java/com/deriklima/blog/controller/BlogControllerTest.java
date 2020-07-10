package com.deriklima.blog.controller;

import static com.deriklima.blog.controller.ResourcePath.ADMIN_BLOG_EDIT_POST_PAGE;
import static com.deriklima.blog.controller.ResourcePath.ADMIN_BLOG_LIST_POSTS_PAGE;
import static com.deriklima.blog.controller.ResourcePath.ADMIN_BLOG_PATH;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.deriklima.blog.dto.EditBlogPostDTO;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
class BlogControllerTest extends BaseControllerTest {

  @Test
  @WithMockUser
  void shouldReturnBlogPostsViewWithListOfBlogPosts() throws Exception {
    mockMvc.perform(get(ADMIN_BLOG_PATH + "/list"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("posts"))
        .andExpect(view().name(ADMIN_BLOG_LIST_POSTS_PAGE));
  }

  @Test
  @WithMockUser
  void shouldReturnNewBlogPostView() throws Exception {
    mockMvc.perform(get(ADMIN_BLOG_PATH + "/new"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("post"))
        .andExpect(view().name(ADMIN_BLOG_EDIT_POST_PAGE));
  }

  @Test
  @WithMockUser
  void shouldSaveNewBlogPost() throws Exception {
    EditBlogPostDTO blogPost = mockBlogPostDTO();
    mockMvc.perform(
        post(ADMIN_BLOG_PATH + "/new")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .flashAttr("post", blogPost)
            .with(csrf()))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/admin/blog/{id}/edit"));
  }

  @Test
  @WithMockUser
  void shouldOpenEditBlogPostPage() throws Exception {
    mockMvc.perform(get(ADMIN_BLOG_PATH + "/1/edit"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("post"))
        .andExpect(view().name(ADMIN_BLOG_EDIT_POST_PAGE));
  }

  @Test
  @WithMockUser
  void shouldEditBlogPostSuccessfully() throws Exception {
    String blogPostId = "1";

    EditBlogPostDTO blogPost = mockBlogPostDTO();
    blogPost.setTags(Collections.emptyList());
    blogPost.setId(Long.parseLong(blogPostId));

    mockMvc.perform(
        post(ADMIN_BLOG_PATH + "/" + blogPostId + "/edit")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("blogPostId", blogPostId)
            .flashAttr("post", blogPost)
            .with(csrf()))
        .andExpect(status().is2xxSuccessful())
        .andExpect(view().name("/admin/blog/edit-post"));
  }

  private EditBlogPostDTO mockBlogPostDTO() {
    return EditBlogPostDTO.builder()
        .title("New Blog Post")
        .description("New Blog Post Description")
        .markdownContent("## H2")
        .status("DRAFT")
        .imgRef("imgref")
        .build();
  }

}