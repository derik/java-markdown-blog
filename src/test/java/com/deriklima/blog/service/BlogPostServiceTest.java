package com.deriklima.blog.service;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.deriklima.blog.dto.EditBlogPostDTO;
import com.deriklima.blog.exception.BlogPostNotFoundException;
import com.deriklima.blog.model.PostStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.transaction.TransactionSystemException;

@SpringBootTest
@SqlGroup({
    @Sql(
        executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:db/h2/schema.sql", "classpath:db/h2/data.sql"}),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/h2/drop.sql")
})
class BlogPostServiceTest {

  @Autowired
  BlogPostService blogPostService;

  @Test
  public void shouldReturnTheNumberOfBlogPosts() {
    assertThat(blogPostService.findAll().size(), equalTo(3));
  }

  @Test
  public void shouldReturnTheNumberOfPublishedBlogPosts() {
    assertThat(blogPostService.findAllPublished().size(), equalTo(2));
  }

  @Test
  public void shouldThrowExceptionWhenFetchingDraftBlogPostUsingFindByIdPublished() {
    long draftBlogPostId = 3L;
    assertThrows(BlogPostNotFoundException.class, () -> {
      blogPostService.findByIdPublished(draftBlogPostId);
    }, "BlogPost with id " + draftBlogPostId + " was not found");
  }

  @Test
  public void shouldSaveANewBlogPostSuccessfuly() {
    String newBlogPostDesc = "New Blog Post Description";
    EditBlogPostDTO dto = EditBlogPostDTO.builder()
        .title("New Blog Post")
        .description(newBlogPostDesc)
        .markdownContent("## Blog Post")
        .status(PostStatus.DRAFT.name())
        .imgRef("imgRef.example")
        .build();
    EditBlogPostDTO savedDTO = blogPostService.save(dto);

    assertThat(savedDTO.getId(), notNullValue());
    assertThat(savedDTO.getStatus(), equalTo(PostStatus.DRAFT.name()));
    assertThat(savedDTO.getDescription(), equalTo(newBlogPostDesc));
  }

  @Test
  public void shouldThrowExceptionWhenInvalidBlogPostDTO() {
    EditBlogPostDTO invalidDto = EditBlogPostDTO.builder()
        .build();

    assertThrows(TransactionSystemException.class, () -> {
      blogPostService.save(invalidDto);
    });
  }

}