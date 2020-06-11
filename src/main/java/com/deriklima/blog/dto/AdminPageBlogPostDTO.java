package com.deriklima.blog.dto;

import com.deriklima.blog.model.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AdminPageBlogPostDTO {

  @EqualsAndHashCode.Include
  private Long id;
  private String title;
  private String status;
  private String description;
  private String createdAt;
  private List<Tag> tags;

}
