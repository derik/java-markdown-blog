package com.deriklima.blog.mapper;

import com.deriklima.blog.dto.AdminPageBlogPostDTO;
import com.deriklima.blog.dto.EditBlogPostDTO;
import com.deriklima.blog.dto.MainPageBlogPostDTO;
import com.deriklima.blog.model.BlogPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BlogPostMapper {

  EditBlogPostDTO toEditBlogPostDTO(BlogPost blogPost);

  BlogPost toBlogPost(EditBlogPostDTO editBlogPostDTO);

  MainPageBlogPostDTO toMainPageBlogPostDTO(BlogPost blogPost);

  @Mapping(target = "status", expression = "java(blogPost.getStatus().name())")
  AdminPageBlogPostDTO toAdminPageBlogPostDTO(BlogPost blogPost);
}
