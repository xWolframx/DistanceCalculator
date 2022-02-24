package ru.egor.kulizhnyh.distance.calculator.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Класс для представления таблицы distance из БД и работы с данными. <br>
 * @author Егор Кулижных
 * @version v 1.0.0
 */
@Entity
@Table(name = "distance")
public class Distance {
    /**
     * Поле хранения столбца id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Поле хранения столбца distance.
     */
    @Column(name = "distance")
    private double distance;

    /**
     * Поле хранения столбца id_from_city.
     */
    @Column(name = "id_from_city", updatable = false, insertable = false, nullable = false)
    private int idFromCity;

    /**
     * Поле хранения столбца id_to_city.
     */
    @Column(name = "id_to_city", updatable = false, insertable = false, nullable = false)
    private int idToCity;

    /**
     * Поле хранения столбца id_way.
     */
    @Column(name = "id_way", updatable = false, insertable = false, nullable = false)
    private int idWay;

    /**
     * Констрктор по умолчанию, для инициализации объекта.
     */
    public Distance() {
    }

    /**
     * Функция для полечения id.
     * @return возвращает id
     */
    public int getId() {
        return id;
    }

    /**
     * Функция для установки id.
     * @param id id дистанции
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция для получения дистанции - растояния между городами.
     * @return дистанцию - растояние между городами
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Функция установки дистанции - растояния между городами.
     * @param distance дистанции - растояния между городами
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Функция получения id города, из которого начинается считаться дистанция - расстояние.
     * @return id города из которого, начинается считаться дистанция - расстояние
     */
    public int getIdFromCity() {
        return idFromCity;
    }

    /**
     * Функция установки id города, из которого начинается считаться дистанция - расстояние.
     * @param idFromCity id города, из которого начинается считаться дистанция - расстояние
     */
    public void setIdFromCity(int idFromCity) {
        this.idFromCity = idFromCity;
    }

    /**
     * Функция получения id города, к которму считается расстояние.
     * @return id города, к которму считается расстояние
     */
    public int getIdToCity() {
        return idToCity;
    }

    /**
     * Функция установки id города, к которму считается расстояние.
     * @param idToCity id города, к которму считается расстояние
     */
    public void setIdToCity(int idToCity) {
        this.idToCity = idToCity;
    }

    /**
     * Функция получения id метода, по которому считается дистанция - расстояние между городами.
     * @return id метода, по которому считается дистанция - расстояние между городами
     */
    public int getIdWay() {
        return idWay;
    }

    /**
     * Функция устновки id метода, по которому считается дистанция - расстояние между городами.
     * @param idWay d метода, по которому считается дистанция - расстояние между городами
     */
    public void setIdWay(int idWay) {
        this.idWay = idWay;
    }
}
