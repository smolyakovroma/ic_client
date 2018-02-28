package com.immerscontroller.ic_client;

import com.immerscontroller.ic_client.domain.Device;
import com.immerscontroller.ic_client.repository.DeviceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> findAllDevice(){
        return deviceRepository.findAll();
    }

    public Device findOne(int id){
        return deviceRepository.findOne(id);
    }

    public Device findByAddress(String address){
        return deviceRepository.findByAddress(address);
    }

    public Device save(Device device){
        return deviceRepository.save(device);
    }

    public List<Device> scan(String address){
        List<Device> devices = new ArrayList<>();

        Authenticator.setDefault(new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("root", "root".toCharArray());
            }
        });

        //here u have your own IP address..now suppose your IP is 192.168.1.1.basically m sending hello packet to every other machine.if that machine is active ,it will reply n we get to know that machine is active......
        for (int i = 1; i < 255; i++) {
            String ip_address = address + i;//192.168.1.1,192.168.1.2 n so on.........to 192.168.1.255
//            try {
//
//                Socket s = new Socket(n, 80);
//                System.out.println("Alive in address " + n);
//                s.close();
//            } catch (IOException ex) {
//                // The remote host is not listening on this port
//                System.out.println("No life on " + n);
//            }
//
//
//            Connection connect = HttpConnection.connect("http://" + ip_address + "/cgi-bin/minerStatus.cgi");
//            connect.timeout(5);
//            try {
//                Document document = connect.get();
//            } catch (IOException e) {
//                System.out.println("No life on " + ip_address);
//            }

            Document doc = null;

            try {
//                doc = Jsoup.connect("http://" + ip_address + "/cgi-bin/minerStatus.cgi").timeout(1000).get();
                doc = Jsoup.connect("http://" + ip_address +":8080").timeout(1000).get();
                Device device = new Device();
                device.setName(ip_address);
                device.setId(1);
                devices.add(device);

            } catch (IOException e) {
                System.out.println("No life on " + ip_address);
            }



        }

        return devices;
    }

    public Device parseDevice(String address){

        Device device = new Device();
        Authenticator.setDefault(new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("root", "root".toCharArray());
            }
        });


        Document doc = null;

        try {
//            doc = Jsoup.connect("http://192.168.0.101/cgi-bin/minerStatus.cgi").get();
            doc = Jsoup.connect(address).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String minertype = doc.select("#ant_minertype").first().text();
        device.setName(minertype);
        device.setAddress(address);
        return device;

//        Elements asic_freq = doc.select("#cbi-table-1-frequency");
//        Elements asic_temp = doc.select("#cbi-table-1-temp2");
//        Elements asic_hash_ideal = doc.select("#cbi-table-1-rate2");
//        Elements asic_hash_rt = doc.select("#cbi-table-1-rate");
//
//
//        Element asic_fan_1 = doc.select("#ant_fan3").first();
//        System.out.println("freq");
//        Element asic_fan_2 = doc.select("#ant_fan6").first();
//        for (Element element : asic_freq) {
//            System.out.println(element.text());
//        }
//        System.out.println("temp");
//        for (Element element : asic_temp) {
//            System.out.println(element.text());
//        }
//        System.out.println("hash_ideal");
//        for (Element element : asic_hash_ideal) {
//            System.out.println(element.text());
//        }
//        System.out.println("hash_rt");
//        for (Element element : asic_hash_rt) {
//            System.out.println(element.text());
//        }
//        System.out.println("fan speed");
//        System.out.println(asic_fan_1.text());
//        System.out.println(asic_fan_2.text());
//

//
    }
}
