package com.triangular.recipe_yc.service;

import com.triangular.recipe_yc.dto.RecipeInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface RecipeService {
    @PreAuthorize("isAuthenticated()")
    RecipeInfo saveRecipe(RecipeInfo recipeInfo, MultipartFile picture);

    RecipeInfo getRecipe(UUID recipeId);

    @PreAuthorize("isAuthenticated()")
    RecipeInfo editRecipe(UUID recipeId, RecipeInfo recipeInfo, MultipartFile picture);

    @PreAuthorize("isAuthenticated()")
    void deleteRecipe(UUID recipeId);

    List<RecipeInfo> getRecipeList();
}
