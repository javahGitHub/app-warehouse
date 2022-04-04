package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.IndividualDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.service.ClientService;
import pdp.uz.appwarehouse.service.ProviderService;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping
    public Result addProviderController(@RequestBody IndividualDto individualDto){
        return clientService.addClient(individualDto);
    }

    @GetMapping
    public Page getProviders(@RequestParam Integer page){
        return clientService.getClient(page);
    }

    @PostMapping("/update/{id}")
    public Result updateProvider(@PathVariable Integer id, @RequestBody IndividualDto individualDto){
        return clientService.updateClient(individualDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteProvider(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }


}
