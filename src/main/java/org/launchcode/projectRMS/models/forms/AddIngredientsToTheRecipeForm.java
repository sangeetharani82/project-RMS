package org.launchcode.projectRMS.models.forms;

import org.launchcode.projectRMS.models.Ingredient;
import org.launchcode.projectRMS.models.Recipe;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddIngredientsToTheRecipeForm {

    private Recipe recipe;
    private Iterable<Ingredient> ingredients;

    @NotNull
    @Size(min=3, max=15)
    private String quantity;

    @NotNull
    private int recipeId;

    @NotNull
    private int ingredientId;

    public AddIngredientsToTheRecipeForm(Recipe recipe, Iterable<Ingredient> ingredients, String quantity) {
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.quantity = quantity;
    }

    public AddIngredientsToTheRecipeForm() {
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Iterable<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }
}
