package com.deriklima.blog.controller.formatters;

import com.deriklima.blog.dto.TagDTO;
import java.util.Locale;
import org.springframework.format.Formatter;

public class TagFormatter implements Formatter<TagDTO> {

  @Override
  public TagDTO parse(String id, Locale locale) {
    TagDTO tagDTO = new TagDTO();
    tagDTO.setId(Long.parseLong(id));
    return tagDTO;
  }

  @Override
  public String print(TagDTO tagDTO, Locale locale) {
    return tagDTO.getId().toString();
  }
}
