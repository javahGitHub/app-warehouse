package pdp.uz.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.DTO.WorkerDto;
import pdp.uz.appwarehouse.entity.WareHouse;
import pdp.uz.appwarehouse.entity.Worker;
import pdp.uz.appwarehouse.repository.WareHouseRepository;
import pdp.uz.appwarehouse.repository.WorkerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    WareHouseRepository wareHouseRepository;


    //Add Worker
    public Result addWorkerService(WorkerDto workerDto) {
        //List to collect error warehouses id
        List errors=null;
        //List to collect warehouses
        Set<WareHouse> wareHouseList =null;

        //Check phone number of Worker in data
        if(workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber()))
            return new Result("Worker phone number already exist", false);

        //Check password of Worker in data
        if(workerRepository.existsByPassword(workerDto.getPassword()))
            return new Result("Worker password already exist: Enter other password", false);

        //Check and Collect all WareHouse from repository
        for (int i = 0; i < workerDto.getWareHousesId().size(); i++) {
            Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(workerDto.getWareHousesId().get(i));
            //Collect exist warehouse
            if(optionalWareHouse.isPresent())
            wareHouseList.add(optionalWareHouse.get());
            //Collect error warehouse id
            else
            errors.add(i+1);
        }
        //Return message all warehouse error id with false                                       ↓ error ids ↓
        if(!errors.isEmpty() || errors!=null || wareHouseList.isEmpty())
            return  new Result("WareHouses id not found  in repository when entered id="+errors.toString(), false);


        // to save
        Worker worker=new Worker();
        // Set all fields of Worker --->
        worker.setFirstName(workerDto.getFirstName());
        worker.setLastName(workerDto.getLastName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setPosition(workerDto.getPosition());
        worker.setCode(generateCode(workerDto.getFirstName()));
        worker.setPassword(worker.getPassword());
        worker.setActive(workerDto.isActive());
        worker.setWareHouse(wareHouseList);
        // <---

        return new Result("New Worker added successfully", true);
    }/* ++++++++=Method For Generate code=+++++++++  */
    private static String generateCode(String name){
        double random=Math.random()*1000;
        return (random-random%1)+"#"+name;
    }

    //Get all Workers
    public Page getAllWorkers(int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Worker> workerPage = workerRepository.findAll(pageable);
        return workerPage;
    }

    //Get  Workers by WareHouse id
    public Page getWorkersByWhId(int page,int id){
        Pageable pageable= PageRequest.of(page,10);
        Page allByWareHouseId = workerRepository.getAllByWareHouseId(pageable, id);
        return allByWareHouseId;
    }

    //Update Worker
    public Result updateWorker(int id,WorkerDto workerDto){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if(!optionalWorker.isPresent())
            return new Result("Worker not found by id",false);
        Worker worker=optionalWorker.get();

        //List to collect error warehouses id
        List errors=null;
        //List to collect warehouses
        Set<WareHouse> wareHouseList =null;

        //Check phone number of Worker in data
        if(workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber()))
            return new Result("Worker phone number already exist", false);

        //Check password of Worker in data
        if(workerRepository.existsByPassword(workerDto.getPassword()))
            return new Result("Worker password already exist: Enter other password", false);

        //Check and Collect all WareHouse from repository
        for (int i = 0; i < workerDto.getWareHousesId().size(); i++) {
            Optional<WareHouse> optionalWareHouse = wareHouseRepository.findById(workerDto.getWareHousesId().get(i));
            //Collect exist warehouse
            if(optionalWareHouse.isPresent())
                wareHouseList.add(optionalWareHouse.get());
                //Collect error warehouse id
            else
                errors.add(i+1);
        }
        //Return message all warehouse error id with false                                       ↓ error ids ↓
        if(!errors.isEmpty() || errors!=null || wareHouseList.isEmpty())
            return  new Result("WareHouses id not found  in repository when entered id="+errors.toString(), false);


        // Set all fields of Worker --->
        worker.setFirstName(workerDto.getFirstName());
        worker.setLastName(workerDto.getLastName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setPosition(workerDto.getPosition());
        worker.setCode(generateCode(workerDto.getFirstName()));
        worker.setPassword(worker.getPassword());
        worker.setActive(workerDto.isActive());
        worker.setWareHouse(wareHouseList);
        // <---

        return new Result(" Worker updated successfully", true);

    }

    //Delete Worker
    public Result deleteWorker(int id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if(!optionalWorker.isPresent())
            return new Result("Worker not found by id",false);
        Worker worker=optionalWorker.get();

        workerRepository.deleteById(id);
        return new Result("Worker deleted successfully",true);
    }

}
