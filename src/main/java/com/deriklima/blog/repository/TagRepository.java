package com.deriklima.blog.repository;

import com.deriklima.blog.model.Tag;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {

  Optional<Tag> findByName(String tagName);

}
