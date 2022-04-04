package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.InputProductsDto;
import pdp.uz.appwarehouse.DTO.OutputProductsDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.*;
import pdp.uz.appwarehouse.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class OutputProductsService {

    @Autowired
    OutputProductsRepository outputProductsRepository;
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    ProductRepository productRepository;


    //Add Output Product
    public Result addOutputProducts(OutputProductsDto outputProductsDto) throws ParseException {

        //Check Output id
        Optional<Output> optionalOutput = outputRepository.findById(outputProductsDto.getOutputId());
        if(!optionalOutput.isPresent())
            return new Result("Output product not found ",false);


        //Check Product id
        Optional<Product> optionalProduct = productRepository.findById(outputProductsDto.getProductId());
        if(!optionalProduct.isPresent())
            return  new Result("Product not found when id="+outputProductsDto.getProductId(),false);

        //Check Measurement id
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(outputProductsDto.getMeasurementId());
        if(!optionalMeasurement.isPresent())
            return  new Result("Measurement not found when id="+outputProductsDto.getMeasurementId() ,false);



        OutputProducts outputProducts=new  OutputProducts();
        outputProducts.setProduct(optionalProduct.get());
        outputProducts.setAmount(outputProductsDto.getAmount());
        outputProducts.setPrice(outputProductsDto.getPrice());
        outputProducts.setMeasurement(optionalMeasurement.get());
        outputProducts.setOutput(optionalOutput.get());
        outputProductsRepository.save(outputProducts);
        return  new Result("Output Product saved successfully" ,true);

    }


    //Read all outputProducts
    public Page getAllOutputProducts(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<OutputProducts> outputProductsPage = outputProductsRepository.findAll(pageable);
        return outputProductsPage;
    }

    //Read all outputProducts by input id
    public Page getAllOutputProductsByInputId(int page,int inId){
        //Check Output id
        Optional<Output> optionalOutput = outputRepository.findById(inId);
        if(!optionalOutput.isPresent())
            return null;


        Pageable pageable=PageRequest.of(page,10);
        Page outputProductsRepositoryAllByInputId = outputProductsRepository.findAllByOutputId(inId, pageable);
        return outputProductsRepositoryAllByInputId;

    }

    //Update Output Product
    public Result updateOutputProducts(OutputProductsDto outputProductsDto,int id) throws ParseException {

        Optional<OutputProducts> optionalOutputProducts = outputProductsRepository.findById(id);
        if(!optionalOutputProducts.isPresent())
            return  new Result("Output product not found ",false);


        //Check Output id
        Optional<Output> optionalOutput = outputRepository.findById(outputProductsDto.getOutputId());
        if(!optionalOutput.isPresent())
            return new Result("Output  not found ",false);


        //Check Product id
        Optional<Product> optionalProduct = productRepository.findById(outputProductsDto.getProductId());
        if(!optionalProduct.isPresent())
            return  new Result("Product not found when id="+outputProductsDto.getProductId(),false);

        //Check Measurement id
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(outputProductsDto.getMeasurementId());
        if(!optionalMeasurement.isPresent())
            return  new Result("Measurement not found when id="+outputProductsDto.getMeasurementId() ,false);



        OutputProducts outputProducts=optionalOutputProducts.get();
        outputProducts.setProduct(optionalProduct.get());
        outputProducts.setAmount(outputProductsDto.getAmount());
        outputProducts.setPrice(outputProductsDto.getPrice());
        outputProducts.setMeasurement(optionalMeasurement.get());
        outputProducts.setOutput(optionalOutput.get());
        outputProductsRepository.save(outputProducts);
        return  new Result("Output Product updated successfully" ,true);
    }

    //Delete Output Product
    public Result deleteOutputProduct(int id){
        if(outputProductsRepository.existsById(id))
            return  new Result("Output Product not found" ,false);

        outputProductsRepository.deleteById(id);
        return   new Result("OutputProduct deleted successfully" ,true);
    }

}
