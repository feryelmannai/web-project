package com.spoon.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spoon.demo.model.Recipe;
import com.spoon.demo.model.enums.Category;
import com.spoon.demo.service.RecipeService;

@RestController
@RequestMapping("api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // Create a new Recipe
    @PostMapping
    public ResponseEntity<Recipe> createRecipe(
            @RequestParam("name") String name,
            @RequestParam("time") Long time,
            @RequestParam("category") Category category,
            @RequestParam("description") String description,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("steps") String steps,
            @RequestParam("author") String author,
            @RequestParam(value = "videoUrl", required = false) String videoUrl,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        try {
            Recipe recipe = new Recipe();
            recipe.setName(name);
            recipe.setTime(time);
            recipe.setCategory(category);
            recipe.setDescription(description);
            recipe.setIngredients(ingredients);
            recipe.setSteps(steps);
            recipe.setAuthor(author);
            recipe.setVideoUrl(videoUrl);

            if (image != null && !image.isEmpty()) {
                String imagePath = saveImage(image);
                recipe.setImagePath(imagePath);
            }

            return ResponseEntity.ok(recipeService.createRecipe(recipe));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private String saveImage(MultipartFile image) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path staticUploadsPath = Paths.get("src/main/resources/static/uploads/");
        Files.createDirectories(staticUploadsPath); // Create directory if not exists
        Path imagePath = staticUploadsPath.resolve(fileName);
        Files.write(imagePath, image.getBytes());
        return "/uploads/" + fileName; // Correct URL path returned
    }

    // Get all recipes
    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    // Get a recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        return recipe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a recipe by ID
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, updatedRecipe));
    }

    // Delete a recipe by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
