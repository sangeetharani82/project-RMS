package org.launchcode.projectRMS.Controllers;

import org.launchcode.projectRMS.models.Ingredient;
import org.launchcode.projectRMS.models.data.IngredientDao;
import org.launchcode.projectRMS.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("ingredient")
public class IngredientController {
    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Ingredients");
        model.addAttribute("ingredients", ingredientDao.findAll());
        return "ingredient/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddForm(Model model){
        model.addAttribute("title", "Add an ingredient");
        model.addAttribute("ingredient", new Ingredient());
        return "ingredient/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(Model model, @ModelAttribute @Valid Ingredient ingredient, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add an ingredient");
            return "ingredient/add";
        }
        ingredientDao.save(ingredient);
        return "redirect:";
    }

    @RequestMapping(value="edit/{ingredientId}", method = RequestMethod.GET)
    public String displayEditIngredientForm(Model model, @PathVariable int ingredientId){
        model.addAttribute(ingredientDao.findOne(ingredientId));
        model.addAttribute("title", "Edit");
        model.addAttribute("ingredientName", ingredientDao.findOne(ingredientId).getIngredientName());
        return "ingredient/edit";
    }

    @RequestMapping(value = "edit/{ingredientId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int ingredientId, @RequestParam String ingredientName){
        Ingredient edited = ingredientDao.findOne(ingredientId);
        edited.setIngredientName(ingredientName);
        ingredientDao.save(edited);
        return "redirect:/ingredient";
    }
}
