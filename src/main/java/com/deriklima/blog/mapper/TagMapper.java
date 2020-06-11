package com.deriklima.blog.mapper;

import com.deriklima.blog.dto.TagDTO;
import com.deriklima.blog.model.Tag;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

  TagDTO tagToTagDTO(Tag tag);

  Tag tagDTOToTag(TagDTO tagDTO);

  List<TagDTO> tagsToTagsDTO(Iterable<Tag> tags);

  List<Tag> tagsDTOToTag(List<TagDTO> tagsDTO);

}
