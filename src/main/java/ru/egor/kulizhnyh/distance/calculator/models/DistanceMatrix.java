package ru.egor.kulizhnyh.distance.calculator.models;

import javax.persistence.*;

/**
 * Класс для представления таблицы distance_matrix из БД и работы с данными. <br>
 * @author Егор Кулижных
 * @version v 1.0.0
 */
@Entity
@Table(name = "distance_matrix")
public class DistanceMatrix {
    /**
     * Поле хранения столбца id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Поле хранения матрицы расстояний.
     */
    @Column(name = "matrix")
    private byte[] matrix;

    /**
     * Конструктор для инициализации объекта.
     */
    public DistanceMatrix() {}

    /**
     * Конструктор инициализации объекта с заданыыми параметрами. Id объекта и матрицы расстояний.
     * @param id id объекта
     * @param matrix марица расстояний
     */
    public DistanceMatrix(int id, byte[] matrix) {
        this.id = id;
        this.matrix = matrix;
    }

    /**
     * Функция для полечения id.
     * @return id матрицы расстояний
     */
    public int getId() {
        return id;
    }

    /**
     * Функция установки id.
     * @param id id матрицы расстояний
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция получения матрицы расстояний.
     * @return матрица расстояний
     */
    public byte[] getMatrix() {
        return matrix;
    }

    /**
     * Функция установки матрицы расстояний
     * @param matrix матрица расстояний
     */
    public void setMatrix(byte[] matrix) {
        this.matrix = matrix;
    }
}
