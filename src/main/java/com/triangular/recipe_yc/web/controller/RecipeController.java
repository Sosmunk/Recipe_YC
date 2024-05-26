package com.triangular.recipe_yc.web.controller;

import com.triangular.recipe_yc.dto.RecipeInfo;
import com.triangular.recipe_yc.service.RecipeService;
import com.triangular.recipe_yc.web.annotation.ApiV1;
import com.triangular.recipe_yc.web.response.EmptyResponse;
import com.triangular.recipe_yc.web.response.ItemResponse;
import com.triangular.recipe_yc.web.response.ListResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@ApiV1
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeController {

    RecipeService recipeService;

    @PostMapping(value = "/recipe/create")
    public ItemResponse<RecipeInfo> addRecipe(@RequestPart("recipeInfo") RecipeInfo recipeInfo,
                                              @RequestPart("image") MultipartFile picture) {

        return new ItemResponse<>(recipeService.saveRecipe(recipeInfo, picture));
    }

    @GetMapping(value = "/recipe/view/{id}")
    public ItemResponse<RecipeInfo> getRecipe(@PathVariable UUID id) {
        return new ItemResponse<>(recipeService.getRecipe(id));
    }

    @GetMapping(value = "/recipe/list")
    public ListResponse<RecipeInfo> getRecipeList() {
        return new ListResponse<>(recipeService.getRecipeList());
    }

    @PostMapping(value = "/recipe/edit/{id}")
    public ItemResponse<RecipeInfo> editRecipe(@PathVariable UUID id,
                                               @RequestPart("recipeInfo") RecipeInfo recipeInfo,
                                               @Nullable @RequestPart("image") MultipartFile picture) {
        return new ItemResponse<>(recipeService.editRecipe(id, recipeInfo, picture));
    }

    @DeleteMapping(value = "/recipe/delete/{id}")
    public EmptyResponse deleteRecipe(@PathVariable UUID id) {
        recipeService.deleteRecipe(id);
        return new EmptyResponse();
    }
}
