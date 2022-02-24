package ru.egor.kulizhnyh.distance.calculator.exceptions;

/**
 * Исключение сообщающее, что не было найдена дистанция между городами при помощи матрицы расстояний. Либо в матрице расстояний физически нет
 * (нет такого расстояния с таким иедксом в матрице), либо их нет, т.е. в матрице по заданному индексу стоит -1.
 * @author Егор Кулижных
 * @version v 1.0.0
 * @see ru.egor.kulizhnyh.distance.calculator.DTO.MatrixDTO
 */
public class NotDistanceFoundException extends Exception {
    public NotDistanceFoundException () {
        super("Not found distance");
    }
}
