package com.triangular.recipe_yc.web.controller;

import com.triangular.recipe_yc.dto.RecipeInfo;
import com.triangular.recipe_yc.service.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeController {

    RecipeService recipeService;

    @PostMapping(value="/create")
    public RecipeInfo addRecipe(@RequestPart("recipeInfo") RecipeInfo recipeInfo,
                                            @RequestPart("image") MultipartFile picture) {

        return recipeService.saveRecipe(recipeInfo, picture);
    }

    @GetMapping(value = "/view/{id}")
    public RecipeInfo getRecipe(@PathVariable UUID id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping(value = "/edit/{id}")
    public RecipeInfo editRecipe(@PathVariable UUID id,
                                RecipeInfo recipeInfo,
                                @Nullable @RequestPart("image") MultipartFile picture) {
        return recipeService.editRecipe(id, recipeInfo, picture);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteRecipe (@PathVariable UUID id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
