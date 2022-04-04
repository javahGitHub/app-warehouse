package pdp.uz.appwarehouse.service;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pdp.uz.appwarehouse.DTO.CategoryDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Category;
import pdp.uz.appwarehouse.repository.CategoryRepository;

import java.awt.geom.RectangularShape;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategoryService(CategoryDto categoryDto) {
        Category category = new Category();
        category.setNames(category.getNames());
        if (categoryDto.getCategoryParentId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
            if (!optionalCategory.isPresent())
                return new Result("Parent Category not found", false);
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category saved successfully", true);
    }

    public Page getCategoryService(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage;
    }

    public Result updateCategoryService(CategoryDto categoryDto, int id) {
        //Check category id from repository
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Category not found", false);

        //Get category from repository as optional
        Category category = optionalCategory.get();

        //Check that category is not main parent
        if (categoryDto.getCategoryParentId() != null) {
            //Check parent category id from repository
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getCategoryParentId());
            if (!optionalParentCategory.isPresent())
                return new Result("Parent Category not found", false);
            category.setNames(category.getNames());
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category updated successfully", true);
    }

    public Result deleteCategoryService( int id) {
        //Check category id from repository
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Category not found", false);
        categoryRepository.deleteById(id);
        return  new Result("Category deleted successfully", true);
    }

    }