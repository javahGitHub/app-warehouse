package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.InputDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Currency;
import pdp.uz.appwarehouse.entity.Input;
import pdp.uz.appwarehouse.entity.Provider;
import pdp.uz.appwarehouse.entity.WareHouse;
import pdp.uz.appwarehouse.repository.CurrencyRepository;
import pdp.uz.appwarehouse.repository.InputRepository;
import pdp.uz.appwarehouse.repository.ProviderRepository;
import pdp.uz.appwarehouse.repository.WareHouseRepository;

import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    CurrencyRepository  currencyRepository;


    public Result addInput(InputDto inputDto){

        //Check WareHouse id
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(inputDto.getWareHouseId());
        if(!optionalWareHouse.isPresent())
            return new Result("Warehouse not found ",false);

        //Check Provider id
        Optional<Provider> optionalProvider = providerRepository.findById(inputDto.getProviderId());
        if(!optionalWareHouse.isPresent())
            return new Result("Provider not found ",false);

        //Check Currency id
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if(!optionalWareHouse.isPresent())
            return new Result("Currency not found ",false);

        Input input=new Input();
        input.setWareHouse(optionalWareHouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setProvider(optionalProvider.get());
        input.setDate(inputDto.getDate());
        input.setCode(generateCode("input"));
        input.setInvoiceNumber(inputDto.getInvoiceNumber());
        inputRepository.save(input);
        return new Result("Input saved successfully",true);

    }/* ++++++++=Method For Generate code=+++++++++  */
    private static String generateCode(String name){
        double random=Math.random()*100000;
        return (random-random%1)+"#"+name;
    }


    //Read all Inputs
    public Page getAllInputs(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Input> inputPage = inputRepository.findAll(pageable);
        return inputPage;
    }

    //Read all Inputs by WareHouse id
    public Page getAllInputs(int page,int whId){
        Pageable pageable= PageRequest.of(page,10);
        Page<Input> inputPage = inputRepository.findAllByWareHouseId(whId,pageable);
        return inputPage;
    }


    //Update Input
    public Result updateInput(InputDto inputDto,int id){

        //Check Input id
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(!optionalInput.isPresent()) {
            return new Result("input not found ",false);
        }

        //Check WareHouse id
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(inputDto.getWareHouseId());
        if(!optionalWareHouse.isPresent())
            return new Result("Warehouse not found ",false);

        //Check Provider id
        Optional<Provider> optionalProvider = providerRepository.findById(inputDto.getProviderId());
        if(!optionalWareHouse.isPresent())
            return new Result("Provider not found ",false);

        //Check Currency id
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if(!optionalWareHouse.isPresent())
            return new Result("Currency not found ",false);





        Input input=optionalInput.get();
        input.setWareHouse(optionalWareHouse.get());
        input.setCurrency(optionalCurrency.get());
        input.setProvider(optionalProvider.get());
        input.setDate(inputDto.getDate());
        input.setCode(generateCode("input"));
        input.setInvoiceNumber(inputDto.getInvoiceNumber());
        inputRepository.save(input);
        return new Result("Input saved successfully",true);

    }

    //Delete Input
    public Result deleteInput(int id){
        //Check Input id
        Optional<Input> optionalInput = inputRepository.findById(id);
        if(!optionalInput.isPresent()) {
            return new Result("input not found ",false);
        }

        inputRepository.deleteById(id);
        return new Result("Input deleted successfully",true);

    }


}
