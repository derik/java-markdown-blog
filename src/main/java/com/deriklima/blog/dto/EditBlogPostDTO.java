package com.deriklima.blog.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
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
public class EditBlogPostDTO {

  @EqualsAndHashCode.Include
  private Long id;

  @NotBlank(message = "{blogpost.status.NotBlank}")
  private String status;

  @NotBlank(message = "{blogpost.title.NotBlank}")
  private String title;

  @NotBlank(message = "{blogpost.description.NotBlank}")
  private String description;
  private String imgRef;
  private String markdownContent;
  private String createdAt;
  private List<TagDTO> tags = new ArrayList<>();

}
