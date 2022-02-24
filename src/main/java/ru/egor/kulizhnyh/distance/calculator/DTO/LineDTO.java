package ru.egor.kulizhnyh.distance.calculator.DTO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Класс для представления строки в матрице расстояний класса MatrixDTO. <br>
 * Данный класс содержит список чисел - расстояний между городами. <br>
 * @author Егор Кулижных
 * @version v 1.0.0
 */
@XmlRootElement(name = "linedto")
public class LineDTO implements Serializable {
    /**
     * Список расстояний между городами.
     */
    @XmlElement(name = "number")
    public ArrayList<Double> line;

    /**
     * Конструктор, инициализирует список расстояний между городами.
     */
    public LineDTO() {
        line = new ArrayList<>();
    }

    /**
     * Функция добавления строки - списка расстоний между городами.
     * @param line список расстояний
     */
    public void setLine(ArrayList<Double> line) {
        this.line = line;
    }

    /**
     * Функция добавления нового числа - расстояний между городами.
     * @param number число - расстояние между городами
     */
    public void addNumber(double number) {
        line.add(number);
    }

    /**
     * Функция возвращает строковое представление объекта.
     * @return строковое предстваление объекта
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Double number: this.line) {
            output.append(number).append(" ");
        }
        output.append("\n");
        return output.toString();
    }
}
