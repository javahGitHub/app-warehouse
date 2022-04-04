package pdp.uz.appwarehouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.ProductDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProductsController(@RequestBody ProductDto productDto){
        return   productService.addProductService(productDto);
    }

    @GetMapping
    public Page getProductsController(@RequestParam int page){
       return productService.getProductsService(page);

    }

    @PostMapping("/update/{id}")
    public Result updateProductsController(@RequestBody ProductDto productDto,@PathVariable Integer id){
       return productService.updateProductsController(productDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public Result updateProductsController(@PathVariable Integer id){
        return productService.deleteProductsService(id);
    }


}
