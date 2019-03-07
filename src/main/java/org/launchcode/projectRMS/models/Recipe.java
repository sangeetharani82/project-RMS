package org.launchcode.projectRMS.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Pattern(regexp = "[^0-9]*")
    @Size(min = 3, max = 100)
    private String recipeName;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Category category;

    @NotNull
    private int servingSize;

    @NotNull
    @Size(min = 3, message = "Prep time needed")
    private String  prepTime;

    @NotNull
    @Size(min = 3, message = "Cook time needed")
    private String cookTime;

    @NotNull
    @Size(min=3, max = 3000000)
    private String direction;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<RateComment> rateCommentList;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<IngredientAndQuantity> ingredientAndQuantityList;

    public Recipe(String recipeName, int servingSize, String prepTime, String cookTime, String direction) {
        this();
        this.recipeName = recipeName;
        this.servingSize = servingSize;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.direction = direction;
    }

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<RateComment> getRateCommentList() {
        return rateCommentList;
    }

    public List<IngredientAndQuantity> getIngredientAndQuantityList() {
        return ingredientAndQuantityList;
    }

    public void setIngredientAndQuantityList(IngredientAndQuantity item) {
        ingredientAndQuantityList.add(item);
    }

    public void setRateCommentList(RateComment rating) {
        rateCommentList.add(rating);
    }
}
