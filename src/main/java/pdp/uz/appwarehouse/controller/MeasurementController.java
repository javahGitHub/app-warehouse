package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Measurement;
import pdp.uz.appwarehouse.service.MeasurementService;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;
    //Add Measurement
    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }
    //Read Measurement
    @GetMapping
    public Page getListMeasurement(@RequestParam int page) {
        Page measurementService= this.measurementService.getMeasurementService(page);
        return measurementService;
    }
    //Update Measurement
    @PostMapping("/update/{id}")
    public Result updateMeasurementController(@PathVariable int id,@RequestBody Measurement measurement){
       return measurementService.updateMeasurementService(id,measurement);
    }
    //Delete Measurement
    @DeleteMapping("/delete/{id}")
    public Result deleteMeasurementController(@PathVariable int id){
       return measurementService.deleteMeasurementService(id);

    }


}
