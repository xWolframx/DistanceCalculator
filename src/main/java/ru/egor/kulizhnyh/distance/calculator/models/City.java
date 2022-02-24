package ru.egor.kulizhnyh.distance.calculator.models;

import javax.persistence.*;

/**
 * Класс для представления таблицы city из БД и работы с данными. <br>
 * @author Егор кулижных
 * @version v 1.0.0
 */
@Entity
@Table(name = "city")
public class City {
    /**
     * Поле хранения столбца id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Поле хранения имени города.
     */
    @Column(name = "name")
    private String name;

    /**
     * Поле хранения широты города.
     */
    @Column(name = "latitude")
    private int latitude;

    /**
     * Поле хранения долготы города.
     */
    @Column(name = "longitude")
    private int longitude;

    /**
     * Констрктор по умолчанию, для инициализации объекта.
     */
    public City() {}

    /**
     * Функция для полечения id.
     * @return id города
     */
    public int getId() {
        return id;
    }

    /**
     * Функция для установки id.
     * @param id id города
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция установки имени города.
     * @return имя города
     */
    public String getName() {
        return name;
    }

    /**
     * Функция установки имени города.
     * @param name имя города
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения широты города.
     * @return широта города
     */
    public int getLatitude() {
        return latitude;
    }

    /**
     * Функция установки широты города.
     * @param latitude широта города
     */
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * Функция получение долготы города.
     * @return доллгота города
     */
    public int getLongitude() {
        return longitude;
    }

    /**
     * Функция установки долготы города.
     * @param longitude долгота города
     */
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
