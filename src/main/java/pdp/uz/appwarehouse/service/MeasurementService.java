package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Measurement;
import pdp.uz.appwarehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        //Check measurement by its name in data before add
        boolean exists = measurementRepository.existsByNames(measurement.getNames());
        if (exists)
            return new Result("Measurement name already exist", false);
        //Save measurement
        measurementRepository.save(measurement);

        return new Result("Measurement added successfully", true);
    }

    public Page getMeasurementService(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page measurementPage=measurementRepository.findAll(pageable);
        return measurementPage;
    }

    public Result updateMeasurementService(int id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if(!optionalMeasurement.isPresent())
            return new Result("Measurement not found",false);
        Measurement originMeasurement = optionalMeasurement.get();
        originMeasurement.setNames(measurement.getNames());
        measurementRepository.save(originMeasurement);
        return new Result("Measurement updated successfully",true);
    }

    public Result deleteMeasurementService(int id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if(!optionalMeasurement.isPresent())
            return new Result("Measurement not found",false);
        measurementRepository.deleteById(id);
        return new Result("Measurement deleted successfully",true);
    }
}
