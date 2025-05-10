package com.spoon.demo.service;

import com.spoon.demo.model.Recipe;
import com.spoon.demo.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Transactional
    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setName(updatedRecipe.getName());
                    recipe.setTime(updatedRecipe.getTime());
                    recipe.setImagePath(updatedRecipe.getImagePath());
                    recipe.setCategory(updatedRecipe.getCategory());
                    recipe.setIngredients(updatedRecipe.getIngredients());
                    recipe.setSteps(updatedRecipe.getSteps());
                    recipe.setDescription(updatedRecipe.getDescription());
                    recipe.setRating(updatedRecipe.getRating());
                    recipe.setLikesCount(updatedRecipe.getLikesCount());
                    recipe.setVideoUrl(updatedRecipe.getVideoUrl());
                    recipe.setAuthor(updatedRecipe.getAuthor());
                    return recipeRepository.save(recipe);
                })
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
