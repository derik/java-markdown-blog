package com.deriklima.blog.service;

import com.deriklima.blog.dto.TagDTO;
import com.deriklima.blog.mapper.TagMapper;
import com.deriklima.blog.model.Tag;
import com.deriklima.blog.repository.TagRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  private final TagMapper tagMapper;

  public List<TagDTO> findAll() {
    return tagMapper.tagsToTagsDTO(tagRepository.findAll());
  }

  @Transactional(readOnly = true)
  public Optional<TagDTO> findTagByName(String tagName) {
    Optional<Tag> tag = tagRepository.findByName(tagName);
    if (tag.isPresent()) {
      return Optional.of(tagMapper.tagToTagDTO(tag.get()));
    } else {
      return Optional.empty();
    }
  }

  @Transactional
  public TagDTO save(TagDTO tagDTO) {
    Tag tag = tagMapper.tagDTOToTag(tagDTO);
    Tag savedTag = tagRepository.save(tag);
    return tagMapper.tagToTagDTO(savedTag);
  }

  @Transactional
  public void deleteById(Long id) {
    tagRepository.deleteById(id);
  }
}
