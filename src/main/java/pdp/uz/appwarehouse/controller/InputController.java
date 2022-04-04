package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.InputDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
           return inputService.addInput(inputDto);
    }

    @GetMapping
    public Page getInputs(@RequestParam int page){
        return  inputService.getAllInputs(page);
    }

    @GetMapping("/getAllInputsByWareHouseId/{id}")
    public Page getInputsByWhId(@RequestParam int page,@PathVariable int id){
        return inputService.getAllInputs(page);
    }

    @PostMapping("/update/{id}")
    public Result  updateInput(@PathVariable int id,@RequestBody InputDto inputDto){
       return inputService.updateInput(inputDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteInput(@PathVariable int id){
        return inputService.deleteInput(id);
    }
}
