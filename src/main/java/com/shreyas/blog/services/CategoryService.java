package com.shreyas.blog.services;
import com.shreyas.blog.domain.dtos.CategoryDto;
import java.util.List;
import java.util.UUID;


public interface CategoryService {
  List<CategoryDto> listCategories();
  CategoryDto createCategory(CategoryDto categoryDto);
  void deleteCategory(UUID categoryId);
}
