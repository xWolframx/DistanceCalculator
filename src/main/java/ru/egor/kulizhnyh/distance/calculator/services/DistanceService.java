package ru.egor.kulizhnyh.distance.calculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egor.kulizhnyh.distance.calculator.DTO.MatrixDTO;
import ru.egor.kulizhnyh.distance.calculator.models.City;
import ru.egor.kulizhnyh.distance.calculator.models.Distance;
import ru.egor.kulizhnyh.distance.calculator.projectionclasses.DistancePC;
import ru.egor.kulizhnyh.distance.calculator.repositorys.DistanceRepository;

import java.util.List;

/**
 * Класс обеспечивает работу с данными. Добавление/чтение данных из БД. <br>
 * Так же выполняет расчёт дистанций - расстояний между говордами по двум методам: Crowflight и матрицы дистанций. <br>
 * Важно отметить работу, метода addDistances(List&lt;Distance&gt; distanceList), не добавит в БД дистанцию, в которой поле distance = -1,
 * т.к. это означает, что при попытке рассчитать дистанцию между городами (при помощи метода calculatedMatrixDistance(City fromCity, City toCity)) при помощи матрицы расстояний либо нету данных о расстоянии между городами
 * (т.е. в матрице расстояний дистанция между городами указана как -1), либо дистанции между городами нет физически,
 * в самой матрице нету данных о расстоянии между этих городов.
 * @author Егор Кулижных
 * @version v 1.0.0
 * @see MatrixDTO
 */
@Service
public class DistanceService {
    /**
     * Объект используемый для работы с дистанциями. В частности для чтения/записи данных из БД.
     */
    @Autowired
    private DistanceRepository distanceRepository;
    /**
     * Объект используемый для работы с матрицей расстояний. В частности для извлечения данных о расстоянии между городами.
     */
    @Autowired
    private MatrixDTO matrixDTO;
    /**
     * Объект используемый для работы с БД. В частности для извлечения матрицы расстояний из БД.
     */
    @Autowired
    private DistanceMatrixService distanceMatrixService;
    /**
     * Объект используемый для работы с городами. В частности для чтения данных о городах из БД.
     */
    @Autowired
    private CityService cityService;

    /**
     * Функция получения списка всех городов из БД.
     * @return List&lt;Distance&gt; список всех гордов из БД
     */
    public List<Distance> getAll() {
        return distanceRepository.findAll();
    }

    /**
     * Функция добавления списка дистанций List&lt;Distance&gt; в БД. <br>
     * Не добавит в БД дистанцию, в которой поле distance = -1. Т.к. это означает, что при попытке рассчитать дистанцию между городами (при помощи метода calculatedMatrixDistance(City fromCity, City toCity)) при помощи матрицы расстояний либо нету данных о расстоянии между городами
     * (т.е. в матрице расстояний дистанция между городами указана как -1), либо дистанции между городами нет физически,
     * в самой матрице нету данных о расстоянии между этих городов.
     * @param distanceList список дистанций
     */
    public void addDistances(List<Distance> distanceList) {
        for (Distance distance: distanceList) {
            if (distance.getDistance() != -1) {
                distanceRepository.addDistance(distance.getIdFromCity(), distance.getIdToCity(), distance.getIdWay(), distance.getDistance());
            }
        }
    }

    /**
     * Функция расчёта дистанций - расстояний между городами методом Crowflight.
     * @param fromCity город, с которогоначинается рассчёт расстояния
     * @param toCity город, к которому рассчитывается расстояние
     * @return расстояние между городами
     */
    public double calculatedCrowflight(City fromCity, City toCity) {
        return Math.sqrt(Math.pow(toCity.getLatitude() - fromCity.getLatitude(), 2) + Math.pow(toCity.getLongitude() - fromCity.getLongitude(), 2));
    }

    /**
     * Функция расчёта дистанций - расстояний между городами при помощи матрицы расстояний.
     * @param fromCity город, с которогоначинается рассчёт расстояния
     * @param toCity город, к которому рассчитывается расстояние
     * @return расстояние между городами
     */
    public double calculatedMatrixDistance(City fromCity, City toCity) {
        matrixDTO.matrixDTODiserialization(distanceMatrixService.getMatrixByte());
        double distance = matrixDTO.getDistance(fromCity.getId(), toCity.getId());
        return distance;
    }

    /**
     * Функция расчёта расстояний между городами.
     * @param distanceList список дистанций
     * @return список дистанций типа DistancePC
     * @see DistancePC
     */
    public List<DistancePC> calculatedDistances(List<Distance> distanceList) {
        for (Distance distance: distanceList) {
            City fromCity = cityService.getById(distance.getIdFromCity());
            City toCity = cityService.getById(distance.getIdToCity());
            long idWay = distance.getIdWay();

            if (idWay == 1) {
                distance.setDistance(calculatedCrowflight(fromCity, toCity));
            }
            if (idWay == 2) {
                distance.setDistance(calculatedMatrixDistance(fromCity, toCity));
            }
        }

        addDistances(distanceList);

        return DistancePC.distanceListToDistancePCList(distanceList);
    }
}
