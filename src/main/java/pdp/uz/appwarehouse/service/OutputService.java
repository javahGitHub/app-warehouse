package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.InputDto;
import pdp.uz.appwarehouse.DTO.OutputDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.*;
import pdp.uz.appwarehouse.repository.*;

import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addOutput(OutputDto outputDto){

        //Check WareHouse id
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(outputDto.getWareHouseId());
        if(!optionalWareHouse.isPresent())
            return new Result("Warehouse not found ",false);

        //Check Provider id
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if(!optionalClient.isPresent())
            return new Result("Provider not found ",false);

        //Check Currency id
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if(!optionalWareHouse.isPresent())
            return new Result("Currency not found ",false);

        Output output=new Output();
        output.setWareHouse(optionalWareHouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setDate(outputDto.getDate());
        output.setCode(generateCode("output"));
        output.setInvoiceNumber(outputDto.getInvoiceNumber());
        outputRepository.save(output);
        return new Result("Output saved successfully",true);

    }/* ++++++++=Method For Generate code=+++++++++  */
    private static String generateCode(String name){
        double random=Math.random()*100000;
        return (random-random%1)+"#"+name;
    }


    //Read all Outputs
    public Page getAllOutputs(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Output> inputPage = outputRepository.findAll(pageable);
        return inputPage;
    }

    //Read all Outputs by WareHouse id
    public Page getAllOutputs(int page,int whId){
        Pageable pageable= PageRequest.of(page,10);
        Page<Output> outputPage  = outputRepository.findAllByWareHouseId(whId,pageable);
        return outputPage;
    }


    //Update Output
    public Result updateOutput(OutputDto outputDto,int id){

        //Check Output id
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(!optionalOutput.isPresent()) {
            return new Result("Output not found ",false);
        }

        //Check WareHouse id
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(outputDto.getWareHouseId());
        if(!optionalWareHouse.isPresent())
            return new Result("Warehouse not found ",false);

        //Check Provider id
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if(!optionalClient.isPresent())
            return new Result("Client not found ",false);

        //Check Currency id
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if(!optionalCurrency.isPresent())
            return new Result("Currency not found ",false);





        Output output=optionalOutput.get();
        output.setWareHouse(optionalWareHouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setDate(output.getDate());
        output.setCode(generateCode("output"));
        output.setInvoiceNumber(outputDto.getInvoiceNumber());
        outputRepository.save(output);
        return new Result("Output saved successfully",true);

    }

    //Delete Output
    public Result deleteOutput(int id){
        //Check Output id
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if(!optionalOutput.isPresent()) {
            return new Result("Output not found ",false);
        }

        outputRepository.deleteById(id);
        return new Result("Output deleted successfully",true);

    }


}
