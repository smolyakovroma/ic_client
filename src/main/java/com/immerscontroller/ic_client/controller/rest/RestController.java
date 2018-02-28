package com.immerscontroller.ic_client.controller.rest;

import com.immerscontroller.ic_client.DeviceService;
import com.immerscontroller.ic_client.domain.Device;
import com.immerscontroller.ic_client.domain.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.TimeUnit;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Test test() {
        return new Test(1, "HELLO");
    }

    @RequestMapping(value = "/test2/{name}", method = RequestMethod.GET)
    public Test test2(@PathVariable String name) {
        return new Test(2, "Hello " + name);
    }

    @RequestMapping(value = "/scan/{address}", method = RequestMethod.POST)
    public List<Device> scan(@PathVariable String address) {
        List<Device> list = deviceService.scan(address);
        return list;
    }

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public List<Device> findAllDevice() {
        return deviceService.findAllDevice();
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    public void save(@RequestParam String name, @RequestParam String address) {

        if (deviceService.findByAddress(address.trim()) == null) {
            Device device = deviceService.parseDevice(address);
            deviceService.save(device);
        }
    }

    @RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
    public Device findOne(@PathVariable int id) {
        return deviceService.findOne(id);
    }
}
