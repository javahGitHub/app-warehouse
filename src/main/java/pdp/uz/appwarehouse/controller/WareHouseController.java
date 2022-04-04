package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Currency;
import pdp.uz.appwarehouse.entity.Measurement;
import pdp.uz.appwarehouse.entity.WareHouse;
import pdp.uz.appwarehouse.service.CurrencyService;
import pdp.uz.appwarehouse.service.WareHouseService;

@RestController
@RequestMapping("/warehouse")
public class WareHouseController {

    @Autowired
    WareHouseService wareHouseService;

    //Add WareHouse
    @PostMapping
    public Result addMeasurementController(@RequestBody WareHouse wareHouse){
        return wareHouseService.addWareHouseService(wareHouse);
    }
    //Read WareHouse
    @GetMapping
    public Page getListMeasurement(@RequestParam int page) {
        return wareHouseService.getWareHouseService(page);
    }
    //Update WareHouse
    @PostMapping("/update/{id}")
    public Result updateMeasurementController(@PathVariable int id, @RequestBody WareHouse wareHouse){
        return wareHouseService.updateWareHouseService(id,wareHouse);
    }
    //Delete WareHouse
    @DeleteMapping("/delete/{id}")
    public Result deleteMeasurementController(@PathVariable int id){
        return wareHouseService.deleteWareHouseService(id);

    }

}
