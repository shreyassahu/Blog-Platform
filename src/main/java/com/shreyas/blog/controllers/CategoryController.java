package com.shreyas.blog.controllers;

import com.shreyas.blog.domain.dtos.CategoryDto;
import com.shreyas.blog.domain.dtos.CreateCategoryRequest;
import com.shreyas.blog.mappers.CategoryMapper;
import com.shreyas.blog.services.CategoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;
  private final CategoryMapper categoryMapper;

  @GetMapping
  public ResponseEntity<List<CategoryDto>> listCategories() {
    return new ResponseEntity<>(categoryService.listCategories(), HttpStatusCode.valueOf(200));
  }

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(
          @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
    CategoryDto categoryDto = categoryMapper.toDtoFromRequest(createCategoryRequest);
    return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
    categoryService.deleteCategory(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
