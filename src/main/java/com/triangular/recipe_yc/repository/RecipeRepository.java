package com.triangular.recipe_yc.repository;

import com.triangular.recipe_yc.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecipeRepository extends CrudRepository<Recipe, UUID> {
}
