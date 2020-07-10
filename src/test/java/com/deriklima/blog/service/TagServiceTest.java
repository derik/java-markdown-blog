package com.deriklima.blog.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.deriklima.blog.dto.TagDTO;
import com.deriklima.blog.model.Tag;
import com.deriklima.blog.repository.TagRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@SqlGroup({
    @Sql(
        executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:db/h2/schema.sql", "classpath:db/h2/data.sql"}),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/h2/drop.sql")
})
class TagServiceTest {

  @Autowired
  TagService tagService;

  @Autowired
  TagRepository tagRepository;

  @Test
  void shouldFindAllTags() {
    assertThat(tagService.findAll().size(), equalTo(2));
  }

  @Test
  void shouldFindTagByNameSuccessfully() {
    assertNotNull(tagService.findTagByName("java"));
  }

  @Test
  void shouldReturnEmptyOptionalWhenTagDoesNotExist() {
    assertFalse(tagService.findTagByName("test").isPresent());
  }

  @Test
  void shouldSaveTag() {
    // when
    TagDTO savedTag = tagService.save(TagDTO.builder().name("kotlin").build());

    // then
    assertNotNull(savedTag.getId());
  }

  @Test
  void shouldDeleteTagByIdSuccessfully() {
    // given
    Long id = 1L;

    // when
    tagService.deleteById(id);
    Optional<Tag> tag = tagRepository.findById(id);

    // then
    assertFalse(tag.isPresent());
  }

}