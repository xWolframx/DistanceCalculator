package ru.egor.kulizhnyh.distance.calculator.DTO;

import org.springframework.stereotype.Component;
import ru.egor.kulizhnyh.distance.calculator.models.City;
import ru.egor.kulizhnyh.distance.calculator.models.Distance;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для чтения/записи списков новых городов и дистанций в/из xml - файла. И последующей сериалицации/дисериализации для записи в БД. <br>
 * При написании xml - файла нужно учитывать несколько факторов: <br>
 * 1) Сначала должен идти список добавляемых городов, потом дистанции<br>
 * 2) В БД используется авто-генерация первичных ключей, а это значит, что при написании списка дистанций нужно вписывать idFromCity и idToCity,
 *    тех городов, которые уже есть в БД или те id городов, которые были добавлены непостредственно из данного файла. <br>
 * Пример xml - файла: <br>
 * &lt;xml version="1.0" encoding="UTF-8" standalone="yes"?&gt; <br>
 * &lt;CitiesDistance&gt; <br>
 * <pre>    &lt;city id="6"&gt;</pre>
 * <pre>        &lt;name&gt;Saratov&lt;/name&gt;</pre>
 * <pre>        &lt;latitude&gt;871&lt;/latitude&gt;</pre>
 * <pre>        &lt;longitude&gt;5475&lt;/longitude&gt;</pre>
 * <pre>    &lt;/city&gt;</pre>
 * <pre>    &lt;city id="7"&gt;</pre>
 * <pre>        &lt;name&gt;Vladivostok&lt;/name&gt;</pre>
 * <pre>        &lt;latitude&gt;87141&lt;/latitude&gt;</pre>
 * <pre>        &lt;longitude&gt;547&lt;/longitude&gt;</pre>
 * <pre>    &lt;/city&gt;</pre>
 * <pre>    &lt;distance id="8"&gt;</pre>
 * <pre>        &lt;distance&gt;35754.01&lt;/distance&gt;</pre>
 * <pre>        &lt;idFromCity&gt;6&lt;/idFromCity&gt;</pre>
 * <pre>        &lt;idToCity&gt;2&lt;/idToCity&gt;</pre>
 * <pre>        &lt;idWay&gt;1&lt;/idWay&gt;</pre>
 * <pre>    &lt;/distance&gt;</pre>
 * <pre>&lt;/CitiesDistance&gt;</pre>
 * @author Егор Кулижных
 * @version v 1.0.0
 */
@Component
@XmlRootElement(name = "CitiesDistance")
public class CitiesDistancesDTO {
    /**
     * Список городов.
     */
    private ArrayList<CityDTO> cities;
    /**
     * Список дистанций.
     */
    private ArrayList<DistanceDTO> distances;

    /**
     * Конструктор инициализирует массивы гордов и дистанций.
     */
    public CitiesDistancesDTO() {
        cities = new ArrayList<>();
        distances = new ArrayList<>();
    }

    /**
     * Функция добавления города в список городов cities.
     * @param cityDTO добовлемый город
     * @see CityDTO
     */
    public void addCities(CityDTO cityDTO) {
        cities.add(cityDTO);
    }

    /**
     * Функция добавления расстояния в список расстояний distances.
     * @param distanceDTO добовлемая дистанция
     * @see DistanceDTO
     */
    public void addDistance(DistanceDTO distanceDTO) {
        distances.add(distanceDTO);
    }

    /**
     * Функция возвращения списка городов как ArrayList&lt;CityDTO&gt;.
     * @return ArrayList&lt;CityDTO&gt;
     * @see CityDTO
     */
    @XmlElement(name = "city")
    public ArrayList<CityDTO> getCities() {
        return cities;
    }

    /**
     * Функция возвращения списка дистанций как ArrayList&lt;DistanceDTO&gt;.
     * @return ArrayList&lt;DistanceDTO&gt;
     * @see DistanceDTO
     */
    @XmlElement(name = "distance")
    public ArrayList<DistanceDTO> getDistances() {
        return distances;
    }

    /**
     * Функция записи списков городов и дистаций, cities и distances соотвественно, по указанному пути в xml - файл.
     * Тип список принимаемых в качестве параметров List&lt;CityDTO&gt; и List&lt;DistanceDTO&gt; соответсвенно.
     * @param cityDTOList список городов
     * @param distanceDTOList список дистанций
     * @param path путь куда будет записан файл
     * @see CityDTO
     * @see DistanceDTO
     */
    public void marshal(List<CityDTO> cityDTOList, List<DistanceDTO> distanceDTOList, String path) {
        CitiesDistancesDTO citiesDistancesDTO = new CitiesDistancesDTO();

        if (path.isEmpty()) {
            path = "src/main/resources/CitiesDistances.xml";
        }

        for (CityDTO cityDTO: cityDTOList) {
            citiesDistancesDTO.addCities(cityDTO);
        }
        for (DistanceDTO distanceDTO: distanceDTOList) {
            citiesDistancesDTO.addDistance(distanceDTO);
        }

        try {
            JAXBContext context = JAXBContext.newInstance(CitiesDistancesDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(citiesDistancesDTO, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция записи списков городов и дистаций, cities и distances соотвественно, по указанному пути в xml - файл.
     * Тип список принимаемых в качестве параметров List&lt;City&gt; и List&lt;Distance&gt; соответсвенно.
     * Данный метод может быть полезным при выгрузке данных из БД.
     * @param cityList список городов
     * @param distanceList список дистанций
     * @param path путь куда будет записан файл
     * @see City
     * @see Distance
     */
    public void marshalCityListAndDistanceList(List<City> cityList, List<Distance> distanceList, String path) {
        List<CityDTO> cityDTOList = new ArrayList<>();
        List<DistanceDTO> distanceDTOList = new ArrayList<>();

        for (City city: cityList) {
            CityDTO tempCityDTO = new CityDTO();
            tempCityDTO.setId(city.getId());
            tempCityDTO.setName(city.getName());
            tempCityDTO.setLatitude(city.getLatitude());
            tempCityDTO.setLongitude(city.getLongitude());

            cityDTOList.add(tempCityDTO);
        }

        for (Distance distance: distanceList) {
            DistanceDTO tempDistanceDTO = new DistanceDTO();
            tempDistanceDTO.setId(distance.getId());
            tempDistanceDTO.setDistance(distance.getDistance());
            tempDistanceDTO.setIdFromCity(distance.getIdFromCity());
            tempDistanceDTO.setIdToCity(distance.getIdToCity());
            tempDistanceDTO.setIdWay(distance.getIdWay());

            distanceDTOList.add(tempDistanceDTO);
        }

        marshal(cityDTOList, distanceDTOList, path);
    }

    /**
     * Функция чтения списков городов и дистаций, cities и distances соотвественно, из указанного xml - файл.
     * @param path путь к xml - файла из которого будут считанны данные
     */
    public void unmarshal(String path) {
        CitiesDistancesDTO citiesDistancesDTO = new CitiesDistancesDTO();

        if (!this.distances.isEmpty()) {
            this.distances.clear();
        }
        if (!this.cities.isEmpty()) {
            this.cities.clear();
        }

        try {
            JAXBContext context = JAXBContext.newInstance(CitiesDistancesDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            citiesDistancesDTO = (CitiesDistancesDTO) unmarshaller.unmarshal(new FileReader(path));

            for (CityDTO cityDTO: citiesDistancesDTO.getCities()) {
                CityDTO tempCityDTO = (CityDTO) cityDTO.clone();
                this.cities.add(tempCityDTO);
            }
            for (DistanceDTO distanceDTO: citiesDistancesDTO.getDistances()) {
                DistanceDTO tempDistanceDTO = (DistanceDTO) distanceDTO.clone();
                this.distances.add(tempDistanceDTO);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * Функция преобразует список List&lt;CityDTO&gt; в список List&lt;City&gt;.
     * В частности данная функция используется для того, чтобы получить список городов List&lt;City&gt; из прочитанного xml - файла и добавить данный список в БД.
     * @return List&lt;City&gt;
     * @see City
     * @see CityDTO
     */
    public List<City> cityDTOListToCityList() {
        List<City> cityList = new ArrayList<>();

        for (CityDTO cityDTO: this.cities) {
            cityList.add(cityDTO.cityDTOToCity());
        }

        return cityList;
    }

    /**
     * Функция преобразует список List&lt;DistanceDTO&gt; в список List&lt;Distance&gt;.
     * В частности данная функция используется для того, чтобы получить список расстояний List&lt;Distance&gt; из прочитанного xml - файла и добавить данный список в БД.
     * @return List&lt;Distance&gt;
     * @see Distance
     * @see DistanceDTO
     */
    public List<Distance> distanceDTOListToDistanceList() {
        List<Distance> distanceList = new ArrayList<>();

        for (DistanceDTO distanceDTO: this.distances) {
            distanceList.add(distanceDTO.distanceDTOToDistance());
        }

        return distanceList;
    }

    /**
     * Функция возвращает строковое представление объекта.
     * @return строковое предстваление объекта
     */
    @Override
    public String toString() {
        return "\nCitiesDistancesDTO{" +
                "\ncities=" + cities +
                "\ndistances=" + distances +
                "}\n";
    }
}
