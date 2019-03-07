package org.launchcode.projectRMS.Comparators;

import org.launchcode.projectRMS.models.Category;

import java.util.Comparator;

public class CategoryComparator implements Comparator<Category> {
    @Override
    public int compare(Category o1, Category o2) {
        return o1.getCategoryName().compareTo(o2.getCategoryName());
    }
}
