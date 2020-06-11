package com.deriklima.blog.service;

import com.deriklima.blog.dto.AdminPageBlogPostDTO;
import com.deriklima.blog.dto.EditBlogPostDTO;
import com.deriklima.blog.dto.MainPageBlogPostDTO;
import com.deriklima.blog.mapper.BlogPostMapper;
import com.deriklima.blog.mapper.TagMapper;
import com.deriklima.blog.exception.BlogPostNotFoundException;
import com.deriklima.blog.model.BlogPost;
import com.deriklima.blog.model.PostStatus;
import com.deriklima.blog.repository.BlogPostRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogPostService {

  private final BlogPostRepository blogPostRepository;
  private final BlogPostMapper blogPostMapper;
  private final TagMapper tagMapper;

  @Transactional(readOnly = true)
  public List<MainPageBlogPostDTO> findAllPublished() {
    return StreamSupport
        .stream(blogPostRepository.findAllPublished().spliterator(), false)
        .map(blogPostMapper::toMainPageBlogPostDTO)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<AdminPageBlogPostDTO> findAll() {
    return StreamSupport
        .stream(blogPostRepository.findAll().spliterator(), false)
        .map(blogPostMapper::toAdminPageBlogPostDTO)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public EditBlogPostDTO findById(Long id) {
    return blogPostRepository.findById(id)
        .map(blogPostMapper::toEditBlogPostDTO)
        .orElseThrow(
            () -> new BlogPostNotFoundException("BlogPost with id " + id + " was not found"));
  }

  @Transactional(readOnly = true)
  public EditBlogPostDTO findByIdPublished(Long id) {
    return blogPostRepository.findByIdAndPublished(id)
        .map(blogPostMapper::toEditBlogPostDTO)
        .orElseThrow(
            () -> new BlogPostNotFoundException("BlogPost with id " + id + " was not found"));
  }

  @Transactional(readOnly = true)
  public EditBlogPostDTO findByHandlePublished(String handle) {
    return blogPostRepository.findByHandleAndPublished(handle)
        .map(blogPostMapper::toEditBlogPostDTO)
        .orElseThrow(
            () -> new BlogPostNotFoundException(
                "BlogPost with handle '" + handle + "' was not found"));
  }

  @Transactional
  public EditBlogPostDTO save(EditBlogPostDTO dto) {
    if (dto.getId() != null) {
      Optional<BlogPost> retrievedBlogPost = blogPostRepository.findById(dto.getId());
      BlogPost blogPost = retrievedBlogPost.orElseThrow(RuntimeException::new);
      blogPost.setStatus(PostStatus.valueOf(dto.getStatus()));
      blogPost.setTitle(dto.getTitle());
      blogPost.setDescription(dto.getDescription());
      blogPost.setImgRef(dto.getImgRef());
      blogPost.setMarkdownContent(dto.getMarkdownContent());
      blogPost.setTags(tagMapper.tagsDTOToTag(dto.getTags()));
      return blogPostMapper.toEditBlogPostDTO(blogPost);
    } else {
      BlogPost blogPost = blogPostMapper.toBlogPost(dto);
      blogPost.setHandle(getHandle(dto.getTitle()));
      BlogPost savedBlogPost = blogPostRepository
          .save(blogPost);
      return blogPostMapper.toEditBlogPostDTO(savedBlogPost);
    }
  }

  private String getHandle(String title) {
    return title == null ?
        null :
        title.toLowerCase().replaceAll(" ", "-");
  }

}
