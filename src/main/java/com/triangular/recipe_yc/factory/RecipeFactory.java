package com.triangular.recipe_yc.factory;

import com.triangular.recipe_yc.dto.RecipeInfo;
import com.triangular.recipe_yc.entity.Recipe;
import org.springframework.stereotype.Component;

@Component
public class RecipeFactory {

    public Recipe createRecipeFrom(RecipeInfo recipeInfo) {
        return Recipe.builder()
                .name(recipeInfo.getName())
                .text(recipeInfo.getText())
                .build();
    }

    public RecipeInfo createRecipeInfoFrom(Recipe recipe) {
        return RecipeInfo.builder()
                .name(recipe.getName())
                .text(recipe.getText())
                .picture(recipe.getPicture())
                .build();
    }

    public Recipe assign(Recipe recipe, RecipeInfo recipeInfo, String pictureLink) {
        recipe.setName(recipeInfo.getName());
        recipe.setText(recipeInfo.getText());
        recipe.setPicture(pictureLink);
        return recipe;
    }
}
