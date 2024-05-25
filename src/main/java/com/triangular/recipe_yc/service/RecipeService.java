package com.triangular.recipe_yc.service;

import com.triangular.recipe_yc.dto.RecipeInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface RecipeService {

    RecipeInfo saveRecipe(RecipeInfo recipeInfo, MultipartFile picture);

    RecipeInfo getRecipe(UUID recipeId);

    RecipeInfo editRecipe(UUID recipeId, RecipeInfo recipeInfo, MultipartFile picture);

    void deleteRecipe(UUID recipeId);
}
