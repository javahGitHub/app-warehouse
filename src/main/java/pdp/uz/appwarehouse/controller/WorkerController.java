package pdp.uz.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appwarehouse.DTO.Result;
import pdp.uz.appwarehouse.DTO.WorkerDto;
import pdp.uz.appwarehouse.service.WorkerService;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping
    public Result addWorkerController(@RequestBody WorkerDto workerDto){
         return workerService.addWorkerService(workerDto);
    }

    @GetMapping
    public Page getAllWorkers(@RequestParam int page){
        return workerService.getAllWorkers(page);
    }

    @GetMapping("/getWorkersByWareHouseId/{id}")
    public Page getWorkersByWhId(@RequestParam int page,@PathVariable int id){
      return workerService.getWorkersByWhId(page,id);
    }


    @PostMapping("/update/{id}")
    public Result updateWorker(@PathVariable Integer id,@RequestBody WorkerDto workerDto ){
     return  workerService.updateWorker(id,workerDto);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteWorker(@PathVariable Integer id){
        return  workerService.deleteWorker(id);
    }


}
