package ru.egor.kulizhnyh.distance.calculator.projectionclasses;

import ru.egor.kulizhnyh.distance.calculator.models.Distance;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс проекция, предназначен для вывода дистанций пользователю с сокрытием id дистанции.
 * @author Егор Кулижных
 * @version v 1.0.0
 */
public class DistancePC {
    /**
     * Поле хранения id города, с которого начинается рассчёт расстояния.
     */
    private int idFromCity;
    /**
     * Поле хранения id города, к которому расчитывается расстояние.
     */
    private int idToCity;
    /**
     * Поле хранения id пути, по которому рассчитывается дистанция между городами. Метод Crowflight или матрицы расстояний.
     */
    private int idWay;
    /**
     * Поле хранит дистанцию - расстояние между городами.
     */
    private double distance;

    /**
     * Конструктор по умолчанию, для инициализации объекта.
     */
    public DistancePC() {}

    /**
     * Конструктор для инициализации объекта с задаными параметрами.
     * @param idFromCity id города, с которого начинается рассчёт расстояния
     * @param idToCity id города, к которому расчитывается расстояние
     * @param idWay id пути, по которому рассчитывается дистанция между городами
     * @param distance дистанцию - расстояние между городами
     */
    public DistancePC(int idFromCity, int idToCity, int idWay, double distance) {
        this.idFromCity = idFromCity;
        this.idToCity = idToCity;
        this.idWay = idWay;
        this.distance = distance;
    }

    /**
     * Функция получения id города, с которого начинается рассчёт расстояния.
     * @return id города, с которого начинается рассчёт расстояния
     */
    public int getIdFromCity() {
        return idFromCity;
    }

    /**
     * Функция установки id города, с которого начинается рассчёт расстояния.
     * @param idFromCity id города, с которого начинается рассчёт расстояния
     */
    public void setIdFromCity(int idFromCity) {
        this.idFromCity = idFromCity;
    }

    /**
     * Функция получения id города, к которому расчитывается расстояние.
     * @return id города, к которому расчитывается расстояние
     */
    public int getIdToCity() {
        return idToCity;
    }

    /**
     * Функция устанвоки id города, к которому расчитывается расстояние.
     * @param idToCity id города, к которому расчитывается расстояние
     */
    public void setIdToCity(int idToCity) {
        this.idToCity = idToCity;
    }

    /**
     * Функция получения id пути, по которому рассчитывается дистанция между городами.
     * @return id пути
     */
    public int getIdWay() {
        return idWay;
    }

    /**
     * Функция установки id пути, по которому рассчитывается дистанция между городами.
     * @param idWay id пути
     */
    public void setIdWay(int idWay) {
        this.idWay = idWay;
    }

    /**
     * Функция получения дистанции - расстояния между городами.
     * @return дистанции - расстояния между городами
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Функция установки дистанции - расстояния между городами.
     * @param distance дистанции - расстояния между городами
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Функция преобазует список типа List&lt;Distance&gt; distanceList в список типа List&lt;DistancePC&gt;
     * @param distanceList список дистанций типа List&lt;Distance&gt;
     * @return список дистанций типа List&lt;DistancePC&gt;
     * @see Distance
     */
    public static List<DistancePC> distanceListToDistancePCList(List<Distance> distanceList) {
        List<DistancePC> distancePCList = new ArrayList<>();
        for (Distance distance: distanceList) {
            distancePCList.add(new DistancePC(distance.getIdFromCity(), distance.getIdToCity(), distance.getIdWay(), distance.getDistance()));
        }

        return distancePCList;
    }
}
