package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.InputProductsDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.InputProducts;
import pdp.uz.appwarehouse.service.InputProductsService;

import javax.persistence.GeneratedValue;
import java.text.ParseException;

@RestController
@RequestMapping("input_products")
public class InputProductsController {

    @Autowired
    InputProductsService inputProductsService;


    @PostMapping
    public Result addInputProduct(@RequestBody InputProductsDto inputProductsDto) throws ParseException {
   return inputProductsService.addInputProducts(inputProductsDto);
    }


    @GetMapping
    public Page getAllInputProducts(@RequestParam int page){
        return  inputProductsService.getAllInputProducts(page);
    }

    @GetMapping("/getInProductsByInId/{id}")
    public Page getAllInputProducts(@RequestParam int page,@PathVariable int id){
        return  inputProductsService.getAllInputProductsByInputId(page,id);
    }

    @PostMapping("/update/{id}")
    public Result updateInputProduct(@RequestBody InputProductsDto inputProductsDto,@PathVariable int id) throws ParseException {
        return inputProductsService.updateInputProducts(inputProductsDto,id);
    }


    @DeleteMapping("/delete/{id}")
    public Result deleteInputProduct(@PathVariable int id)
    {
        return inputProductsService.deleteInputProduct(id);
    }
}
