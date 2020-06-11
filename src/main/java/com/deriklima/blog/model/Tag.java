package com.deriklima.blog.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "tags",
    indexes = {
        @Index(name = "tags_name", columnList = "name", unique = true)
    }
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Tag {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tags_sequence")
  @SequenceGenerator(name = "tags_sequence", sequenceName = "tags_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @ToString.Exclude
  @ManyToMany(mappedBy = "tags")
  private List<BlogPost> blogPosts;

}
