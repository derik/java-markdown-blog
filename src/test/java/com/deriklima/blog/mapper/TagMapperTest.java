package com.deriklima.blog.mapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.deriklima.blog.dto.TagDTO;
import com.deriklima.blog.model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TagMapperTest {

  @Autowired
  private TagMapper tagMapper;

  @Test
  void shouldMapDTOToTag() {
    // given
    TagDTO dto = TagDTO.builder().name("java").build();

    // when
    Tag tag = tagMapper.tagDTOToTag(dto);

    // then
    assertThat(tag.getName(), equalTo(dto.getName()));
  }

  @Test
  void shouldMapTagToDTO() {
    // given
    Tag tag = new Tag();
    tag.setName("spring");

    // when
    TagDTO dto = tagMapper.tagToTagDTO(tag);

    // then
    assertThat(dto.getName(), equalTo(tag.getName()));
  }

}