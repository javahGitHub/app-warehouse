package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.IndividualDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.service.ProviderService;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    ProviderService providerService;

    @PostMapping
    public Result addProviderController(@RequestBody IndividualDto individualDto){
        return providerService.addProvider(individualDto);
    }

    @GetMapping
    public Page getProviders(@RequestParam Integer page){
        return  providerService.getProviders(page);
    }

    @PostMapping("/update/{id}")
    public Result updateProvider(@PathVariable Integer id,@RequestBody IndividualDto individualDto){
        return providerService.updateProvider(individualDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteProvider(@PathVariable Integer id){
        return providerService.deleteProvider(id);
    }



}


