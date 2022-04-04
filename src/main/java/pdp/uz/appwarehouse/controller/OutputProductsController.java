package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.InputProductsDto;
import pdp.uz.appwarehouse.DTO.OutputProductsDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Output;
import pdp.uz.appwarehouse.service.InputProductsService;
import pdp.uz.appwarehouse.service.OutputProductsService;

import java.text.ParseException;

@RestController
@RequestMapping("/outputProducts")
public class OutputProductsController {


    @Autowired
    InputProductsService inputProductsService;
    @Autowired
    OutputProductsService outputProductsService;

    @PostMapping
    public Result addOutputProduct(@RequestBody OutputProductsDto outputProductsDto) throws ParseException {
        return outputProductsService.addOutputProducts(outputProductsDto);
    }


    @GetMapping
    public Page getAllOutputProducts(@RequestParam int page){
        return  inputProductsService.getAllInputProducts(page);
    }

    @GetMapping("/getOutProductsByOutId/{id}")
    public Page getAllOutputProducts(@RequestParam int page,@PathVariable int id){
        return  outputProductsService.getAllOutputProductsByInputId(page,id);
    }

    @PostMapping("/update/{id}")
    public Result updateOutputProduct(@RequestBody OutputProductsDto outputProductsDto,@PathVariable int id) throws ParseException {
        return outputProductsService.updateOutputProducts(outputProductsDto,id);
    }


    @DeleteMapping("/delete/{id}")
    public Result deleteOutputProduct(@PathVariable int id)
    {
        return outputProductsService.deleteOutputProduct(id);
    }

}
