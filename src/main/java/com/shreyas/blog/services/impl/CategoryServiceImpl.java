package com.shreyas.blog.services.impl;

import com.shreyas.blog.domain.dtos.CategoryDto;
import com.shreyas.blog.domain.entities.Category;
import com.shreyas.blog.mappers.CategoryMapper;
import com.shreyas.blog.repositories.CategoryRepository;
import com.shreyas.blog.services.CategoryService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  @Override
  public List<CategoryDto> listCategories() {
    return categoryRepository.findAllWithPostCount().stream().map(categoryMapper::toDto).toList();
  }

  @Override
  @Transactional
  public CategoryDto createCategory(CategoryDto categoryDto) {
    if(categoryRepository.existsByNameIgnoreCase(categoryDto.getName())) {
      throw new IllegalArgumentException("Category name already exists");
    }
    Category newCategory = categoryMapper.toEntity(categoryDto);
    System.out.println(newCategory.getId());
    Category savedCategory = categoryRepository.save(newCategory);
    return categoryMapper.toDto(savedCategory);
  }

  @Override
  public void deleteCategory(UUID categoryId) {
    Optional<Category> category = categoryRepository.findById(categoryId);
    if(category.isPresent()) {
      if(!category.get().getPosts().isEmpty()) {
        throw new IllegalStateException("There are posts in the category");
      }
      categoryRepository.deleteById(categoryId);
    }
  }
}
