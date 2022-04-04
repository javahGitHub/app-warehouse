package pdp.uz.appwarehouse.controller;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Currency;
import pdp.uz.appwarehouse.entity.Measurement;
import pdp.uz.appwarehouse.repository.CurrencyRepository;
import pdp.uz.appwarehouse.service.CurrencyService;
import pdp.uz.appwarehouse.service.MeasurementService;

import javax.persistence.Entity;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;
    //Add Currency
    @PostMapping
    public Result addMeasurementController(@RequestBody Currency currency){
        Result result = currencyService.addCurrencyService(currency);
        return result;
    }
    //Read Currency
    @GetMapping
    public Page getListMeasurement(@RequestParam int page) {
        return currencyService.getCurrencyService(page);
    }
    //Update Currency
    @PostMapping("/update/{id}")
    public Result updateMeasurementController(@PathVariable int id, @RequestBody Currency currency){
        return currencyService.updateCurrencyService(id,currency);
    }
    //Delete Currency
    @DeleteMapping("/delete/{id}")
    public Result deleteMeasurementController(@PathVariable int id){
        return currencyService.deleteCurrencyService(id);

    }


}
