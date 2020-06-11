package com.deriklima.blog.repository;

import com.deriklima.blog.model.BlogPost;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends PagingAndSortingRepository<BlogPost, Long> {

  @Override
  @EntityGraph(attributePaths = {"tags"})
  Optional<BlogPost> findById(Long id);

  @Query("SELECT b FROM BlogPost b WHERE b.status = com.deriklima.blog.model.PostStatus.PUBLISHED")
  List<BlogPost> findAllPublished();

  @Query("SELECT b FROM BlogPost b WHERE b.id = :id "
      + "AND b.status = com.deriklima.blog.model.PostStatus.PUBLISHED")
  Optional<BlogPost> findByIdAndPublished(@Param("id") Long id);

  @Query("SELECT b FROM BlogPost b WHERE b.handle = :handle "
      + "AND b.status = com.deriklima.blog.model.PostStatus.PUBLISHED")
  Optional<BlogPost> findByHandleAndPublished(@Param("handle") String handle);
}
