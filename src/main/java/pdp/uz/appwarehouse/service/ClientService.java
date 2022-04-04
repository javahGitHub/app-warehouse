package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.IndividualDto;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Client;
import pdp.uz.appwarehouse.entity.Provider;
import pdp.uz.appwarehouse.repository.ClientRepository;
import pdp.uz.appwarehouse.repository.ProviderRepository;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ClientRepository clientRepository;


    public Result addClient(IndividualDto individualDto){

        Client client=new Client();
        client.setPhoneNumber(individualDto.getPhoneNumber());
        client.setNames(individualDto.getName());
        clientRepository.save(client);
        return new Result("Client added successfully",true);

    }

    public Page getClient(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage;
    }

    public Result updateClient(IndividualDto individualDto,Integer id){

        //Check client in repository
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(!optionalClient.isPresent())
            return  new Result("Client not found",false);
        //Check client phone number in repository
        if(providerRepository.existsByPhoneNumber(individualDto.getPhoneNumber()))
            return new Result("Phone number already exist by other provide",false);
        Client client=optionalClient.get();
        client.setPhoneNumber(individualDto.getPhoneNumber());
        client.setNames(individualDto.getName());
        clientRepository.save(client);
        return new Result("Client updated successfully",true);

    }

    public Result deleteClient(Integer id){
        //Check client in repository
        Optional<Client> optionalClient= clientRepository.findById(id);
        if(!optionalClient.isPresent())
            return  new Result("Client not found",false);
        clientRepository.deleteById(id);
        return new Result("Client deleted successfully",true);
    }

}
