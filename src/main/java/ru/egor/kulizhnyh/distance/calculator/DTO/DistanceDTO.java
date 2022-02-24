package ru.egor.kulizhnyh.distance.calculator.DTO;

import ru.egor.kulizhnyh.distance.calculator.models.Distance;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Класс для представления данных о дистанции из БД. И последующей его записи/чтения в xml - файл. <br>
 * Для более подробной информации о записи/чтения в xml - файл смотрите класс CitiesDistancesDTO.
 * @author Егор Кулижных
 * @version v 1.0.0
 * @see CitiesDistancesDTO
 */
@XmlRootElement(name = "distance")
@XmlType(propOrder = {"id", "distance", "idFromCity", "idToCity", "idWay"})
public class DistanceDTO implements Cloneable {
    /**
     * поле хранения id.
     */
    @XmlAttribute
    private int id;
    /**
     * Поле хранения дистанции между городами.
     */
    @XmlElement
    private double distance;
    /**
     * Поле хранения id города, из которого начитается рассчёт дистанции.
     */
    @XmlElement
    private int idFromCity;
    /**
     * Поле хранит id города, к которому рассчитывается дистанция.
     */
    @XmlElement
    private int idToCity;
    /**
     * Поле хранит id по какому методу рассчитывать путь.
     */
    @XmlElement
    private int idWay;

    /**
     * Функция установки id дистанции
     * @param id id дистанции
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция установки расстония между городами.
     * @param distance расстояние между городами
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Функция установки id города, к которому рассчитывается дистанция.
     * @param idFromCity id города, к которому рассчитывается дистанция
     */
    public void setIdFromCity(int idFromCity) {
        this.idFromCity = idFromCity;
    }

    /**
     * Функция устновки id города, к которому рассчитывается дистанция.
     * @param idToCity id города, к которому рассчитывается дистанция
     */
    public void setIdToCity(int idToCity) {
        this.idToCity = idToCity;
    }

    /**
     * Функция установки id по какому методу рассчитывать путь.
     * @param idWay id по какому методу рассчитывать путь
     */
    public void setIdWay(int idWay) {
        this.idWay = idWay;
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
     * Функция возвращает строковое представление объекта.
     * @return строковое предстваление объекта
     */
    @Override
    public String toString() {
        return "\nDistanceDTO{" +
                "id=" + id +
                ", distance=" + distance +
                ", idFromCity=" + idFromCity +
                ", idToCity=" + idToCity +
                ", idWay=" + idWay +
                "}\n";
    }

    /**
     * Функция преобразет дистанцию из типа DistanceDTO в тип Distance.
     * В частности используется для того, чтобы извлечённые данные из xml - файла, добавить в БД.
     * @return объект дистанции класса Distance
     */
    public Distance distanceDTOToDistance() {
        Distance distance = new Distance();
        distance.setId(this.id);
        distance.setDistance(this.distance);
        distance.setIdFromCity(this.idFromCity);
        distance.setIdToCity(this.idToCity);
        distance.setIdWay(this.idWay);

        return distance;
    }
}
