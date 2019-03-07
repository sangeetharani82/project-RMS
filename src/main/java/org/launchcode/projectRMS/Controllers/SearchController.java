package org.launchcode.projectRMS.Controllers;

import org.launchcode.projectRMS.Comparators.RecipeComparator;
import org.launchcode.projectRMS.models.IngredientAndQuantity;
import org.launchcode.projectRMS.models.Recipe;
import org.launchcode.projectRMS.models.data.CategoryDao;
import org.launchcode.projectRMS.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Enter a search term");
        return "search/result";
    }

    @RequestMapping(value = "results")
    public String results(Model model, @RequestParam String searchTerm){
        RecipeComparator recipeComparator = new RecipeComparator();
        int count = 0;
        ArrayList<Recipe> lists = new ArrayList<>();
        ArrayList<Recipe> newLists = new ArrayList<>();
        for (Recipe recipe : recipeDao.findAll()) {
            if (recipe.getRecipeName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    (recipe.getCategory().getCategoryName().toLowerCase().contains(searchTerm.toLowerCase())) ||
                    (recipe.getCourse().getCourseName().toLowerCase().contains(searchTerm.toLowerCase()))) {
                int id = recipe.getId();
                Recipe recipe1 = recipeDao.findOne(id);
                lists.add(recipe1);
                for(Recipe list : lists){
                    if (!newLists.contains(list)){
                        newLists.add(list);
                    }
                }
                newLists.sort(recipeComparator);
                model.addAttribute("recipes", newLists);
                count = count + 1;
                model.addAttribute("title", count + " item(s) found");
            }else{
                for (IngredientAndQuantity i : recipe.getIngredientAndQuantityList()){
                    if (i.getIngredient().getIngredientName().toLowerCase().contains(searchTerm.toLowerCase())){
                        int id = recipe.getId();
                        Recipe recipe1 = recipeDao.findOne(id);
                        lists.add(recipe1);
                        for(Recipe list : lists){
                            if (!newLists.contains(list)){
                                newLists.add(list);
                            }
                        }
                        newLists.sort(recipeComparator);
                        model.addAttribute("recipes", newLists);
                        count = count + 1;
                        model.addAttribute("title", count + " item(s) found");
                    }
                }
            }
        }

        if (count == 0){
            model.addAttribute("message", "No recipes found under the name " + searchTerm);
            return "recipe/message";
        }
        return "search/result";
    }
}
