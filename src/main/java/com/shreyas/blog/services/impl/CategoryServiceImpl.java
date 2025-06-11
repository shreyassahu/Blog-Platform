package com.shreyas.blog.services.impl;

import com.shreyas.blog.domain.dtos.CategoryDto;
import com.shreyas.blog.domain.entities.Category;
import com.shreyas.blog.mappers.CategoryMapper;
import com.shreyas.blog.repositories.CategoryRepository;
import com.shreyas.blog.services.CategoryService;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
