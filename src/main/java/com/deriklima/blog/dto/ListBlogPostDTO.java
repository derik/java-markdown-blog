package com.deriklima.blog.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ListBlogPostDTO {

  @EqualsAndHashCode.Include
  private Long id;
  private String status;
  private String title;
  private String description;
  private String createdAt;
  private List<TagDTO> tags = new ArrayList<>();

}
