package com.deriklima.blog.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
    name = "blog_posts",
    indexes = {
        @Index(name = "blog_posts_handle",  columnList="handle", unique = true)
    })
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class BlogPost {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_posts_sequence")
  @SequenceGenerator(name = "blog_posts_sequence", sequenceName = "blog_posts_seq", allocationSize = 1)
  private Long id;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "status", nullable = false)
  @NotNull(message = "{blogpost.status.NotBlank}")
  private PostStatus status;

  @Column(name = "title", unique = true, nullable = false)
  @NotBlank(message = "{blogpost.title.NotBlank}")
  private String title;

  @Column(name = "handle", unique = true, nullable = false)
  private String handle;

  @Column(name = "description", nullable = false)
  @NotBlank
  private String description;

  @Column(name = "img_ref")
  private String imgRef;

  @Column(name = "markdown_content", nullable = false, columnDefinition = "TEXT")
  private String markdownContent;

  @CreatedDate
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "blog_posts_tags",
      joinColumns = @JoinColumn(name = "blog_post_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private List<Tag> tags;

}

