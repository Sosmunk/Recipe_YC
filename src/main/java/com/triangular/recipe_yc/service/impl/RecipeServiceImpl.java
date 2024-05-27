package com.triangular.recipe_yc.service.impl;

import com.triangular.recipe_yc.dto.RecipeInfo;
import com.triangular.recipe_yc.entity.Recipe;
import com.triangular.recipe_yc.factory.RecipeFactory;
import com.triangular.recipe_yc.repository.RecipeRepository;
import com.triangular.recipe_yc.service.FileUploadService;
import com.triangular.recipe_yc.service.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
@Service()
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeServiceImpl implements RecipeService {

    RecipeFactory recipeFactory;

    RecipeRepository recipeRepository;

    FileUploadService fileUploadService;

    @Override
    public RecipeInfo saveRecipe(RecipeInfo recipeInfo, MultipartFile picture) {

        Recipe recipe = recipeFactory.createRecipeFrom(recipeInfo);
        recipeRepository.save(recipe);

        String pictureUrl = fileUploadService.uploadFile(picture, recipe.getId()
                + "/"
                + picture.getOriginalFilename()
        );
        recipe.setPicture(pictureUrl);
        recipeRepository.save(recipe);
        return recipeFactory.createRecipeInfoFrom(recipe);
    }

    @Override
    public RecipeInfo getRecipe(UUID recipeId) {
        return recipeFactory.createRecipeInfoFrom(recipeRepository.findById(recipeId).orElseThrow());
    }

    @Override
    public RecipeInfo editRecipe(UUID recipeId, RecipeInfo recipeInfo, MultipartFile picture) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow();
        Recipe changedRecipe = recipeFactory.assign(recipe, recipeInfo, "NOT IMPLEMENTED");
        recipeRepository.save(changedRecipe);
        return recipeFactory.createRecipeInfoFrom(recipe);
    }

    @Override
    public void deleteRecipe(UUID recipeId) {
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public List<RecipeInfo> getRecipeList() {
        return recipeRepository.findAll()
                .stream()
                .map(recipeFactory::createRecipeInfoFrom)
                .toList();
    }
}
