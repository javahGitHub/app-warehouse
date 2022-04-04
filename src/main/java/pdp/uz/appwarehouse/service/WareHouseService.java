package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Currency;
import pdp.uz.appwarehouse.entity.Measurement;
import pdp.uz.appwarehouse.entity.WareHouse;
import pdp.uz.appwarehouse.repository.CurrencyRepository;
import pdp.uz.appwarehouse.repository.WareHouseRepository;

import java.util.Optional;

@Service
public class WareHouseService {

    @Autowired
    WareHouseRepository wareHouseRepository;

    public Result addWareHouseService(WareHouse wareHouse) {
        //Check WareHouse by its name in data before add
        boolean exists = wareHouseRepository.existsByNames(wareHouse.getNames());
        if (exists)
            return new Result("WareHouse name already exist", false);
        //Save WareHouse
        wareHouseRepository.save(wareHouse);

        return new Result("WareHouse added successfully", true);
    }


    public Page getWareHouseService(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page wareHousePage=wareHouseRepository.findAll(pageable);
        return wareHousePage;
    }

    public Result updateWareHouseService(int id, WareHouse wareHouse){
        Optional<WareHouse> wareHouseOptional = wareHouseRepository.findById(id);
        if(!wareHouseOptional.isPresent())
            return new Result("WareHouse not found",false);
        WareHouse originWareHouse = wareHouseOptional.get();
        originWareHouse.setNames(wareHouse.getNames());
        wareHouseRepository.save(originWareHouse);
        return new Result("WareHouse updated successfully",true);
    }

    public Result deleteWareHouseService(int id) {
        Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(id);
        if(!optionalWareHouse.isPresent())
            return new Result("WareHouse not found",false);
        wareHouseRepository.deleteById(id);
        return new Result("WareHouse deleted successfully",true);
    }



}
