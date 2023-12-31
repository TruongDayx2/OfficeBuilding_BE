package com.example.officebuilding.service.service;

import com.example.officebuilding.dtos.FloorDTO;
import com.example.officebuilding.dtos.ServiceDTO;
import com.example.officebuilding.service.IGeneralService;

public interface IServiceService extends IGeneralService<ServiceDTO> {
    ServiceDTO update(ServiceDTO serviceDTO);
}
