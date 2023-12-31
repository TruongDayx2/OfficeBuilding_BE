package com.example.officebuilding.controller;

import com.example.officebuilding.dtos.RentalDTO;
import com.example.officebuilding.dtos.ServiceContractDTO;
import com.example.officebuilding.service.serviceContract.IServiceContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api", produces = "application/json")
public class ServiceContractController {
    @Autowired
    private IServiceContractService serviceContractService;
    private static final Logger logger = LoggerFactory.getLogger(ServiceContractController.class);
    @PostMapping("/user/serviceContract/create")
    public ResponseEntity<ServiceContractDTO> createNewServiceContract(@RequestBody ServiceContractDTO serviceContractDTO){
        logger.info("Body- {}", serviceContractDTO);
        return new ResponseEntity<>(serviceContractService.save(serviceContractDTO),HttpStatus.OK);
    }

    @GetMapping("/user/serviceContract/getAll")
    public ResponseEntity<List<ServiceContractDTO>> getAllServiceContract(){
        return new ResponseEntity<>(serviceContractService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/user/serviceContract/{id}")
    public ResponseEntity<ServiceContractDTO> getServiceContractById(@PathVariable Integer id){
        Optional<ServiceContractDTO> serviceContract = serviceContractService.findById(id);
        return serviceContract.map(serviceContractDTO -> {
            return new ResponseEntity<>(serviceContractDTO,HttpStatus.OK);
        }).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/user/serviceContract/update/{id}")
    public ResponseEntity<ServiceContractDTO> serviceContractFloor(@PathVariable Integer id, @RequestBody ServiceContractDTO serviceContractDTO){
        logger.info("Body- {}", serviceContractDTO);

        Optional<ServiceContractDTO> serviceContractDTOOptional = serviceContractService.findById(id);
        return serviceContractDTOOptional.map(serviceContractDTO1 -> {
            serviceContractDTO.setId(serviceContractDTO1.getId());
            ServiceContractDTO updateServiceContract = serviceContractService.update(serviceContractDTO);
            return new ResponseEntity<>(updateServiceContract,HttpStatus.OK);
        }).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/user/serviceContract/delete/{id}")
    public ResponseEntity<ServiceContractDTO> deleteServiceContract(@PathVariable Integer id){
        Optional<ServiceContractDTO> serviceContractDTOOptional = serviceContractService.findById(id);
        return serviceContractDTOOptional.map(serviceContractDTO -> {
            serviceContractService.remove(id);
            return new ResponseEntity<>(serviceContractDTO,HttpStatus.OK);
        }).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/serviceContract/findByStatus/{status}")
    public ResponseEntity<List<ServiceContractDTO>> findServiceContractByStatus(@PathVariable Integer status) {
        logger.info("Body- {}", status);

        List<ServiceContractDTO> serviceContracts = serviceContractService.findAllByStatus(status);
        return new ResponseEntity<>(serviceContracts, HttpStatus.OK);
    }

    @GetMapping("user/serviceContract/hide")
    public ResponseEntity<List<ServiceContractDTO>> findServiceContractsWithinDateRange(
            @RequestParam Integer month,
            @RequestParam Integer year
    ) {
        logger.info("Body- {}", month);
        List<ServiceContractDTO> serviceContracts = serviceContractService.findServiceContractsWithinDateRange(month,year);
        return ResponseEntity.ok(serviceContracts);
    }
}
