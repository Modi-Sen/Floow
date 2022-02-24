package com.java.Floow.technicalchallenge.service;


import com.java.Floow.technicalchallenge.bean.DriverBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private DriverService driverService;

    @PostMapping(path="/driver/create")
    public ResponseEntity<DriverBean> addDrivers(@RequestBody DriverBean driverBean){
        DriverBean drivers=driverService.addDrivers(driverBean);
        if(drivers ==null)
            return new ResponseEntity<DriverBean>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<DriverBean>(drivers,HttpStatus.OK);
    }

   @GetMapping(path="/drivers")
   public List<String> retrieveAllUsers() {
       return driverService.findAll();
   }

   @GetMapping(path="/drivers/{date}")
   public List<DriverBean> retrieveUser(@PathVariable("date") String createdAfterDate) {
      return driverService.findByDate(createdAfterDate);
   }
}
