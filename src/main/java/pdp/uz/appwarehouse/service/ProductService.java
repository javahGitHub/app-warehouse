package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pdp.uz.appwarehouse.DTO.ProductDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.*;
import pdp.uz.appwarehouse.repository.AttachmentRepository;
import pdp.uz.appwarehouse.repository.CategoryRepository;
import pdp.uz.appwarehouse.repository.MeasurementRepository;
import pdp.uz.appwarehouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;



    public Result addProductService(ProductDto productDto) {

        //Check category in repository
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found", false);
        //Check image file in repository
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalCategory.isPresent())
            return new Result("Image File not found", false);
        //Check measurement in repository
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalCategory.isPresent())
            return new Result("Measurement not found", false);


        //Save product
        boolean exists = productRepository.existsByNamesAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (!exists)
            return new Result("Same product already exist", false);
        Product product = new Product();
        product.setNames(productDto.getName());
        product.setCode(generateCode(productDto.getName()));//todo regenerate
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Product saved successfully",true);
    }/* ++++++++=Method For Generate code=+++++++++  */
    private static String generateCode(String name){
        return 1+"#"+name;
    }


    public Page getProductsService(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    public Result updateProductsController( ProductDto productDto, Integer id){
        //Check category in repository
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Category not found", false);
        //Check image file in repository
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalCategory.isPresent())
            return new Result("Image File not found", false);
        //Check measurement in repository
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalCategory.isPresent())
            return new Result("Measurement not found", false);
        //Check measurement in repository
        Optional<Product> optionalProduct= productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product not found", false);

        //Update product
        boolean exists = productRepository.existsByNamesAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (!exists)
            return new Result("Same product already exist", false);
        Product product =optionalProduct.get();
        product.setNames(productDto.getName());
        product.setCode(generateCode(productDto.getName()));//todo regenerate
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Product saved successfully",true);
    }

    public Result deleteProductsService(int id){
        //Check measurement in repository
        Optional<Product> optionalProduct= productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product not found", false);
        productRepository.deleteById(id);
        return  new Result("Product deleted successfully", true);
    }
}
