package ru.egor.kulizhnyh.distance.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.egor.kulizhnyh.distance.calculator.DTO.CitiesDistancesDTO;
import ru.egor.kulizhnyh.distance.calculator.DTO.CityDTO;
import ru.egor.kulizhnyh.distance.calculator.DTO.DistanceDTO;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

//		CitiesDistancesDTO citiesDistancesDTO = context.getBean(CitiesDistancesDTO.class);
//
//		CityDTO cityDTO1 = new CityDTO();
//		cityDTO1.setId(4);
//		cityDTO1.setName("Berlin");
//		cityDTO1.setLongitude(15);
//		cityDTO1.setLatitude(17);
//
//		CityDTO cityDTO2 = new CityDTO();
//		cityDTO2.setId(5);
//		cityDTO2.setName("Leningrad");
//		cityDTO2.setLongitude(25);
//		cityDTO2.setLatitude(27);
//
//		List<CityDTO> cityDTOList = new ArrayList<>();
//		cityDTOList.add(cityDTO1);
//		cityDTOList.add(cityDTO2);
//
//
//		DistanceDTO distanceDTO1 = new DistanceDTO();
//		distanceDTO1.setId(6);
//		distanceDTO1.setDistance(3573);
//		distanceDTO1.setIdFromCity(4);
//		distanceDTO1.setIdToCity(5);
//		distanceDTO1.setIdWay(1);
//
//		DistanceDTO distanceDTO2 = new DistanceDTO();
//		distanceDTO2.setId(7);
//		distanceDTO2.setDistance(357);
//		distanceDTO2.setIdFromCity(5);
//		distanceDTO2.setIdToCity(1);
//		distanceDTO2.setIdWay(2);
//
//		List<DistanceDTO> distanceDTOList = new ArrayList<>();
//		distanceDTOList.add(distanceDTO1);
//		distanceDTOList.add(distanceDTO2);
//
//		citiesDistancesDTO.marshal(cityDTOList, distanceDTOList, "");
	}

}
