package ru.egor.kulizhnyh.distance.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.egor.kulizhnyh.distance.calculator.DTO.CitiesDistancesDTO;
import ru.egor.kulizhnyh.distance.calculator.DTO.MatrixDTO;
import ru.egor.kulizhnyh.distance.calculator.models.Distance;
import ru.egor.kulizhnyh.distance.calculator.projectionclasses.CityPC;
import ru.egor.kulizhnyh.distance.calculator.projectionclasses.DistancePC;
import ru.egor.kulizhnyh.distance.calculator.services.CityService;
import ru.egor.kulizhnyh.distance.calculator.services.DistanceMatrixService;
import ru.egor.kulizhnyh.distance.calculator.services.DistanceService;
import ru.egor.kulizhnyh.distance.calculator.services.StorageService;

import java.util.List;

/**
 * Контроллер для управления приложением калькулятор расстояний
 * @author Егор Кулижных
 * @version v 1.0.0
 */
@RestController
@RequestMapping
public class DistanceCalculatorController {
    /**
     * Объект используется для работы с городами. В частности для добавления/извлечения данных таблицы БД city.
     */
    @Autowired
    private CityService cityService;
    /**
     * Объект используется для работы с дистанциями. В частности для добавления/извлечения данных таблицы БД distance.
     */
    @Autowired
    private DistanceService distanceService;
    /**
     * Объект используется для работы с файлами. В частности отвечает за сохранение скачанных файлов на диск.
     */
    @Autowired
    private StorageService storageService;
    /**
     * Объект используется для работы с матрицей дистанций. В частности для добавления/извлечения данных таблицы БД distance_matrix.
     */
    @Autowired
    private DistanceMatrixService distanceMatrixService;
    /**
     * Объект используется для работы с xml - файлами, содержащими матрицу расстояний.
     */
    @Autowired
    private MatrixDTO matrixDTO;
    /**
     * Объект используется для работы с xml - файлами, содержащими списки городов и дистанций.
     */
    @Autowired
    private CitiesDistancesDTO citiesDistancesDTO;

    /**
     * End-point для получения списка городов
     * @return список городов
     */
    @GetMapping("/allCities")
    public List<CityPC> allCities() {
        return cityService.getAllNameId();
    }

    /**
     * End-point для рассчёта расстояний между городами
     * @param distanceList список List&lt;Distance&gt;
     * @return список добавленных дистанций
     */
    @PostMapping("/calculatedDistance")
    public List<DistancePC> calculatedDistances(@RequestBody List<Distance> distanceList) {
        return distanceService.calculatedDistances(distanceList);
    }

    /**
     * End-point для добавления матрицы расстояний. Сначало происходит скачивание xml - файла по заданному пути, парсинг и добавления в БД. <br>
     * По тому как правильно заполнить xml - файл, содержащий матрицу расстояний смтрите в MatrixDTO.
     * @param file xml - файл содержащий матрицу расстояний
     * @return добавленную матрицу расстояний
     * @see MatrixDTO
     */
    @RequestMapping(value = "/addDistanceMatrix", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String addDistanceMatrix(@RequestParam("file") MultipartFile file) {
        storageService.store(file, "src/main/resources/downloadfiles/");

        matrixDTO.unmarshal("src/main/resources/downloadfiles/" + file.getOriginalFilename());

        distanceMatrixService.addMatrix(matrixDTO.matrixDTOSerialization(matrixDTO));

        matrixDTO.matrixDTODiserialization(distanceMatrixService.getMatrixByte());

        return matrixDTO.toString();
    }

    /**
     * End-point добавления новых городов и дистанций в БД из xml - файла. <br>
     * По тому как правильно заполнить xml - файл, содержащий списки гордов и дистанций смотрите в CitiesDistancesDTO.
     * @param file xml - файл, содержайщий списки городов и дистанций
     * @return статус 200 - OK
     * @see CitiesDistancesDTO
     */
    @RequestMapping(value = "/addCityAndDistance", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<?> addCityAndDistance(@RequestParam("file") MultipartFile file) {
        storageService.store(file, "src/main/resources/downloadfiles/");

        citiesDistancesDTO.unmarshal("src/main/resources/downloadfiles/" + file.getOriginalFilename());

        cityService.addCities(citiesDistancesDTO.cityDTOListToCityList());
        distanceService.addDistances(citiesDistancesDTO.distanceDTOListToDistanceList());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
