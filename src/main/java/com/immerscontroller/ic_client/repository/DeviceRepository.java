package com.immerscontroller.ic_client.repository;

import com.immerscontroller.ic_client.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository  extends JpaRepository<Device, Integer> {

    Device findByAddress(String address);
}
