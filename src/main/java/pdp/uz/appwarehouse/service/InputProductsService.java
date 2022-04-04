package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.InputProductsDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.*;
import pdp.uz.appwarehouse.repository.*;

import java.awt.geom.RectangularShape;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class InputProductsService {

    @Autowired
    InputProductsRepository inputProductsRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    ProductRepository productRepository;



    //Add Input Product
    public Result addInputProducts(InputProductsDto inputProductsDto) throws ParseException {

        //Check Input id
        Optional<Input> optionalInput = inputRepository.findById(inputProductsDto.getInputId());
        if(!optionalInput.isPresent()) {
            return new Result("input not found ",false);
        }

        //Check Product id
        Optional<Product> optionalProduct = productRepository.findById(inputProductsDto.getProductId());
        if(!optionalProduct.isPresent())
            return  new Result("Product not found when id="+inputProductsDto.getProductId(),false);

        //Check Measurement id
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(inputProductsDto.getMeasurementId());
        if(!optionalInput.isPresent())
            return  new Result("Measurement not found when id="+inputProductsDto.getMeasurementId() ,false);


        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(inputProductsDto.getExpireDate());
        InputProducts inputProducts=new InputProducts();
        inputProducts.setProduct(optionalProduct.get());
        inputProducts.setExpireDate((java.sql.Date) date);
        inputProducts.setAmount(inputProductsDto.getAmount());
        inputProducts.setPrice(inputProductsDto.getPrice());
        inputProducts.setMeasurement(optionalMeasurement.get());
        inputProducts.setInput(optionalInput.get());
        inputProductsRepository.save(inputProducts);
        return  new Result("InputProduct saved successfully" ,true);

    }

    //Read all inputProducts
    public Page getAllInputProducts(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<InputProducts> inputProductsPage = inputProductsRepository.findAll(pageable);
        return inputProductsPage;
    }

    //Read all inputProducts by input id
    public Page getAllInputProductsByInputId(int page,int inId){
        //Check Input id
        Optional<Input> optionalInput = inputRepository.findById(inId);
        if(!optionalInput.isPresent()) {
            return null;
        }

        Pageable pageable=PageRequest.of(page,10);
        Page productsRepositoryAllByInputId = inputProductsRepository.findAllByInputId(inId, pageable);
        return productsRepositoryAllByInputId;

    }

    //Update Input Product
    public Result updateInputProducts(InputProductsDto inputProductsDto,int id) throws ParseException {

        //Check Input product id
        Optional<InputProducts> optionalInputProducts = inputProductsRepository.findById(id);
        if(!optionalInputProducts.isPresent())
            return new Result("inputProduct not found ",false);


        //Check Input id
        Optional<Input> optionalInput = inputRepository.findById(inputProductsDto.getInputId());
        if(!optionalInput.isPresent()) {
            return new Result("input not found ",false);
        }

        //Check Product id
        Optional<Product> optionalProduct = productRepository.findById(inputProductsDto.getProductId());
        if(!optionalProduct.isPresent())
            return  new Result("Product not found when id="+inputProductsDto.getProductId(),false);

        //Check Measurement id
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(inputProductsDto.getMeasurementId());
        if(!optionalInput.isPresent())
            return  new Result("Measurement not found when id="+inputProductsDto.getMeasurementId() ,false);


        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(inputProductsDto.getExpireDate());
        InputProducts inputProducts=optionalInputProducts.get();
        inputProducts.setProduct(optionalProduct.get());
        inputProducts.setExpireDate((java.sql.Date) date);
        inputProducts.setAmount(inputProductsDto.getAmount());
        inputProducts.setPrice(inputProductsDto.getPrice());
        inputProducts.setMeasurement(optionalMeasurement.get());
        inputProducts.setInput(optionalInput.get());
        inputProductsRepository.save(inputProducts);
        return  new Result("InputProduct updated successfully" ,true);

    }

    //Delete Input Product
    public Result deleteInputProduct(int id){
        if(inputProductsRepository.existsById(id))
            return  new Result("Input Product not found" ,false);

        inputProductsRepository.deleteById(id);
    return   new Result("InputProduct deleted successfully" ,true);
    }



}
