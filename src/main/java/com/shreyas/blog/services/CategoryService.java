package com.shreyas.blog.services;
import com.shreyas.blog.domain.dtos.CategoryDto;
import java.util.List;


public interface CategoryService {
  List<CategoryDto> listCategories();
  CategoryDto createCategory(CategoryDto categoryDto);
}
