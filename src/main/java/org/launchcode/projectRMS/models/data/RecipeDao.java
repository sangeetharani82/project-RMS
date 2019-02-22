package org.launchcode.projectRMS.models.data;

import org.launchcode.projectRMS.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Repository
@Transactional
public interface RecipeDao extends CrudRepository<Recipe, Integer> {
    //List<Recipe>findLatest5Recipes(Pageable pageable);
}
