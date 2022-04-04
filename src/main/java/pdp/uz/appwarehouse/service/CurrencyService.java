package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.entity.Currency;
import pdp.uz.appwarehouse.entity.Measurement;
import pdp.uz.appwarehouse.repository.CurrencyRepository;
import pdp.uz.appwarehouse.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrencyService(Currency currency) {
        //Check Currency by its name in data before add
        boolean exists = currencyRepository.existsByNames(currency.getNames());
        if (exists)
            return new Result("Currency name already exist", false);
        //Save Currency
        currencyRepository.save(currency);

        return new Result("Currency added successfully", true);
    }

    public Page getCurrencyService(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page currencyPage=currencyRepository.findAll(pageable);
        return currencyPage;
    }

    public Result updateCurrencyService(int id, Currency currency){
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if(!currencyOptional.isPresent())
            return new Result("Currency not found",false);
        Currency originCurrency = currencyOptional.get();
        originCurrency.setNames(currency.getNames());
        currencyRepository.save(originCurrency);
        return new Result("Currency updated successfully",true);
    }

    public Result deleteCurrencyService(int id){
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if(!optionalCurrency.isPresent())
            return new Result("Currency not found",false);
        currencyRepository.deleteById(id);
        return new Result("Currency deleted successfully",true);
    }

}
