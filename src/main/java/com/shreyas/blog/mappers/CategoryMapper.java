package com.shreyas.blog.mappers;

import com.shreyas.blog.domain.PostStatus;
import com.shreyas.blog.domain.dtos.CategoryDto;
import com.shreyas.blog.domain.dtos.CreateCategoryRequest;
import com.shreyas.blog.domain.entities.Category;
import com.shreyas.blog.domain.entities.Post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

  @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
  CategoryDto toDto(Category category);

  @Named("calculatePostCount")
  default Long calculatePostCount(List<Post> posts) {
    if(posts == null || posts.isEmpty()) return 0L;
    return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
  }

  CategoryDto toDtoFromRequest(CreateCategoryRequest createCategoryRequest);

  Category toEntity(CategoryDto categoryDto);
}
