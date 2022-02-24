package ru.egor.kulizhnyh.distance.calculator.projectionclasses;

/**
 * Класс проекция, предназначен для вывода города пользователю с указанными id и именем города.
 * @author Егор Кулижных
 * @version v 1.0.0
 */
public class CityPC {
    /**
     * Поле хранит id города.
     */
    private int id;
    /**
     * Поле хранит имя города.
     */
    private String name;

    /**
     * Конструктор для инициализации объекта с заданными парметрами.
     * @param id id города
     * @param name имя города
     */
    public CityPC(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Функция получения id города.
     * @return id города
     */
    public int getId() {
        return id;
    }

    /**
     * Функция установки id города.
     * @param id id города
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция получения имени города.
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
}
