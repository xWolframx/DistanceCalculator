package ru.egor.kulizhnyh.distance.calculator.DTO;

import ru.egor.kulizhnyh.distance.calculator.models.City;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс для представления данных города из БД. И последующей его записи/чтения в xml - файл. <br>
 * Для более подробной информации о записи/чтения в xml - файл смотрите класс CitiesDistancesDTO.
 * @author Егор Кулижных
 * @version v 1.0.0
 * @see CitiesDistancesDTO
 */
@XmlRootElement(name = "city")
@XmlType(propOrder = {"id", "name", "latitude", "longitude"})
public class CityDTO implements Cloneable{
    /**
     * Поле хранения id города.
     */
    @XmlAttribute
    private int id;
    /**
     * Поле хранения имени города.
     */
    @XmlElement
    private String name;
    /**
     * Поле хранения широты - latitude города.
     */
    @XmlElement
    private int latitude;
    /**
     * Поле хранения долготы - longitude города.
     */
    @XmlElement
    private int longitude;

    /**
     * Функция установки id города.
     * @param id id города
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция установки имени города.
     * @param name имя города
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция установки широты города.
     * @param latitude широта города
     */
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    /**
     * Функция установки долготы города.
     * @param longitude долгота города
     */
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    /**
     * Функция возвращает строковое представление объекта.
     * @return строковое предстваление объекта
     */
    @Override
    public String toString() {
        return "\nCityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                "}\n";
    }

    /**
     * Функция клонирования объекта
     * @return клонированный объект
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Функция преобразует город из типа CityDTO в тип City.
     * В частности используется для того, чтобы извлечённые данные из xml - файла, добавить в БД.
     * @return объект города класса City
     */
    public City cityDTOToCity() {
        City city = new City();
        city.setId(this.id);
        city.setName(this.name);
        city.setLatitude(this.latitude);
        city.setLongitude(this.longitude);

        return city;
    }

}
