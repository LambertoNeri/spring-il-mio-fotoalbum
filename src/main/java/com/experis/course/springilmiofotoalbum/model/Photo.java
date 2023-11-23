package com.experis.course.springilmiofotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "photos")
public class Photo {
  // VARIABILI
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Title must not be blank")
  @Size(max = 255, message = "Lenght must be less than 255")
  private String title;

  @NotBlank(message = "Description must not be blank")
  @Lob
  private String description;

  @NotBlank(message = "Url must not be blank")
  @Size(max = 255, message = "Lenght must be less than 255")
  private String url;

  @NotBlank(message = "Visibility must not be blank")
  private Boolean visibility;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @ManyToMany(fetch = FetchType.LAZY)
  private List<Category> categories;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Boolean getVisibility() {
    return visibility;
  }

  public void setVisibility(Boolean visibility) {
    this.visibility = visibility;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }
}
