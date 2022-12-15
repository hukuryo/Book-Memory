package com.example.huku.domain.models.book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.huku.domain.models.user.User;

import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "userId")
  private int userId;

  @NotBlank
  @Column(name = "title")
  @Size(max = 30)
  private String title;

  @NotBlank
  @Column(name = "genre")
  @Size(max = 10)
  private String genre;

  @NotNull
  @Column(name = "favorite")
  @Min(0)
  @Max(5)
  private int favorite;

  @NotBlank
  @Column(name = "content")
  @Size(max = 500)
  private String content;

  @NotBlank
  @Column(name = "publisher")
  private String publisher;

  @NotNull
  @Column(name = "price")
  @Min(0)
  private Integer price;

  // @ManyToOne(optional = true)
	// @JoinColumn(name = "use", referencedColumnName = "id", insertable = false, updatable = false)
  // private User user;
}