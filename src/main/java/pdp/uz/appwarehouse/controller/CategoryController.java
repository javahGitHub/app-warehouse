package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.CategoryDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Category;
import pdp.uz.appwarehouse.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    //Add Category
    @PostMapping
    public Result addCategoryController(@RequestBody CategoryDto categoryDto){
        Result result = categoryService.addCategoryService(categoryDto);
        return  result;
    }

    //Read Category
    @GetMapping
    public Page getCategoryController(@RequestParam int page){
       return categoryService.getCategoryService(page);
    }

    //Update Category
    @PostMapping("/update/{id}")
    public Result updateCategoryController(@RequestBody CategoryDto categoryDto,@PathVariable int id){
         return categoryService.updateCategoryService(categoryDto, id);
    }

    //Delete Category
    @DeleteMapping("/{id}")
    public Result updateCategoryController(@PathVariable int id){
        return categoryService.deleteCategoryService( id);
    }


}
