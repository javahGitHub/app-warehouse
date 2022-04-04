package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.IndividualDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Provider;
import pdp.uz.appwarehouse.repository.ProviderRepository;

import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public Result addProvider(IndividualDto individualDto){

        if(providerRepository.existsByPhoneNumber(individualDto.getPhoneNumber()))
            return new Result("Phone number already exist by other provide",false);
        Provider provider=new Provider();
        provider.setPhoneNumber(individualDto.getPhoneNumber());
        provider.setNames(individualDto.getName());
            providerRepository.save(provider);
        return new Result("Provider added successfully",true);

    }

    public Page getProviders(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Provider> providerPage = providerRepository.findAll(pageable);
        return providerPage;
    }

    public Result updateProvider(IndividualDto individualDto,Integer id){

        //Check provider in repository
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        if(!optionalProvider.isPresent())
            return  new Result("Provider not found",false);
        //Check provider phone number in repository
        if(providerRepository.existsByPhoneNumber(individualDto.getPhoneNumber()))
            return new Result("Phone number already exist by other provide",false);
        Provider provider=optionalProvider.get();
        provider.setPhoneNumber(individualDto.getPhoneNumber());
        provider.setNames(individualDto.getName());
        providerRepository.save(provider);
        return new Result("Provider updated successfully",true);

    }

    public Result deleteProvider(Integer id){
        //Check provider in repository
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        if(!optionalProvider.isPresent())
            return  new Result("Provider not found",false);
        providerRepository.deleteById(id);
        return new Result("Provider deleted successfully",true);
    }
}
