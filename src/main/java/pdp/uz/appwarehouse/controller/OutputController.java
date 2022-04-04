package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.InputDto;
import pdp.uz.appwarehouse.DTO.OutputDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Output;
import pdp.uz.appwarehouse.service.InputService;
import pdp.uz.appwarehouse.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addInput(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }

    @GetMapping
    public Page getInputs(@RequestParam int page){
        return  outputService.getAllOutputs(page);
    }

    @GetMapping("/getAllOutputsByWareHouseId/{id}")
    public Page getInputsByWhId(@RequestParam int page,@PathVariable int id){
        return outputService.getAllOutputs(page,id);
    }

    @PostMapping("/update/{id}")
    public Result  updateInput(@PathVariable int id,@RequestBody OutputDto outputDto){
        return outputService.updateOutput(outputDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteInput(@PathVariable int id){
        return outputService.deleteOutput(id);
    }


}
