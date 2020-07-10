package com.deriklima.blog.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.deriklima.blog.dto.EditBlogPostDTO;
import com.deriklima.blog.model.BlogPost;
import com.deriklima.blog.model.PostStatus;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogPostMapperTest {

  @Autowired
  private BlogPostMapper blogPostMapper;

  @Test
  void shouldMapDTOToBlogPost() {
    String status = "PUBLISHED";
    String title = "Title";
    String description = "Description";
    String imgref = "imgref";
    String markdown = "## markdown";

    EditBlogPostDTO dto = EditBlogPostDTO.builder()
        .status(status)
        .title(title)
        .description(description)
        .imgRef(imgref)
        .markdownContent(markdown)
        .build();

    BlogPost blogPost = blogPostMapper.toBlogPost(dto);

    assertThat(blogPost.getStatus(), is(equalTo(PostStatus.valueOf(status))));
    assertThat(blogPost.getTitle(), is(equalTo(title)));
    assertThat(blogPost.getDescription(), is(equalTo(description)));
    assertThat(blogPost.getImgRef(), is(equalTo(imgref)));
    assertThat(blogPost.getMarkdownContent(), is(equalTo(markdown)));
  }

  @Test
  void shouldMapBlogPostToEditBlogPostDTO() {
    String status = "PUBLISHED";
    String title = "Title";
    String description = "Description";
    String imgref = "imgref";
    String markdown = "## markdown";
    String handle = "title";

    BlogPost blogPost = new BlogPost(1L, PostStatus.PUBLISHED, title, handle, description, imgref,
        markdown,
        LocalDateTime.now(),
        LocalDateTime.now(), null);

    EditBlogPostDTO dto = blogPostMapper.toEditBlogPostDTO(blogPost);

    assertThat(dto.getStatus(), is(equalTo(blogPost.getStatus().name())));
    assertThat(dto.getTitle(), is(equalTo(blogPost.getTitle())));
    assertThat(dto.getDescription(), is(equalTo(blogPost.getDescription())));
    assertThat(dto.getImgRef(), is(equalTo(blogPost.getImgRef())));
    assertThat(dto.getMarkdownContent(), is(equalTo(blogPost.getMarkdownContent())));
  }

}