package org.launchcode.projectRMS.Controllers;

import org.launchcode.projectRMS.models.Category;
import org.launchcode.projectRMS.models.Recipe;
import org.launchcode.projectRMS.models.data.CategoryDao;
import org.launchcode.projectRMS.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        int count = 0;
        //int n = 0;
        ArrayList<Recipe> lists = new ArrayList<>();
        for (Recipe recipe : recipeDao.findAll()) {
            if (recipe.getRecipeName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    (recipe.getCategory().getCategoryName().toLowerCase().contains(searchTerm.toLowerCase()))) {
                int id = recipe.getId();
                Recipe recipe1 = recipeDao.findOne(id);
                lists.add(recipe1);
                model.addAttribute("recipes", lists);
                count = count + 1;
                model.addAttribute("title", count + " item(s) found");
            }
        }
//        for (Category cat : categoryDao.findAll()){
//            if(cat.getCategoryName().toLowerCase().equals(searchTerm.toLowerCase())){
//                int categoryId = cat.getId();
//                Category c = categoryDao.findOne(categoryId);
//                List<Recipe> recipes = c.getRecipes();
//                model.addAttribute("recipes", recipes);
//                n = recipes.size() + count;
//                model.addAttribute("title", n + " item(s) found");
//            }
//        }
//        if (count == 0 && n == 0){
        if (count == 0){
            model.addAttribute("message", "No recipes found under the name " + searchTerm);
            return "recipe/message";
        }
        return "search/result";
    }
}
