package com.demo_h2_database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoH2DatabaseApplication implements CommandLineRunner {

	@Autowired
	CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoH2DatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("El numero de coches en base de datos es: " + carRepository.count());

		CarEntity coche1 = new CarEntity("SEAT","seat500",1950);
		carRepository.save(coche1);

		try{
		System.out.println(carRepository.findByModel("seat500").get());
		}catch(Exception e){
			System.out.println("Error: el coche no se encuentra en la base de datos" + e.getMessage());
		}

		try{
			System.out.println(carRepository.findByModel("fiat500").get());
		}catch(Exception e){
			System.out.println("Error: el coche no se encuentra en la base de datos " + e.getMessage());
		}
		System.out.println("El numero de coches en base de datos es: " + carRepository.count());

	}
}
