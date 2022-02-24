package ru.egor.kulizhnyh.distance.calculator.DTO;

import org.springframework.stereotype.Component;
import ru.egor.kulizhnyh.distance.calculator.exceptions.NotDistanceFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для чтения/записи матрицы расстояний из xml - файла. И последующей сериалицации/дисериализации для записи в БД. Так же данный класс используется для получение
 * данных о расстояний между городами при помощи матрицы расстояний. Для этого предназначениа функция getDistance(int from, int to) <br>
 * Хранит матрицу как массив строк. Строки в свою очередь содержат числа. Строки представлены классом LineDTO. <br>
 * Если не получится найти нужное расстояние между говодами будет выведена ошибка NotDistanceFoundException <br>
 * При написании xml - файла нужно учитывать несколько факторов: <br>
 * 1) Каждая строка (&lt;line&gt;  &lt;/line&gt;) соответсвует конкретному городу, т.е. первая строка должна соответсвовать городу,
 * у которого id в таблице БД равна 1; <br>
 * 2) Дальше внутри тега &lt;line&gt; &lt;/line&gt; перечисленны числа (&lt;number&gt;  &lt;/number&gt;) - расстояний между городами.
 *    Данные числа должны идти в порядке 1, 2, 3 и т.д. Где числа соответсвуют id гордов в таблице БД;<br>
 * 3) Если же данных о расстояний сежду городами нет необходимо поставить внутри тега &lt;number&gt;&lt;/number&gt; -1;<br>
 * 4) Подразумевается, что все необходимые города уже добавлены в БД, хотя это не критично, т.к. таблица, хранящая, матрицу расстояний не связана с таблицей городов. <br>
 * Пример заполнения тега &lt;line&gt; &lt;/line&gt;. Допустим, что у нас есть следующие города в БД: <br>
 * id: 1 name: Samara <br>
 * id: 2 name: Moscow <br>
 * id: 3 name: Saratov <br>
 * При этом данных о расстоянии между Самарой и Саратовом нет. Тогда заполнение тега &lt;line&gt; &lt;/line&gt; будет выглядеть следущим образом: <br>
 * &lt;line&gt;
 * <pre>    &lt;number&gt; 1 &lt;/number&gt;</pre>
 * <pre>    &lt;number&gt; 2154.47 &lt;/number&gt;</pre>
 * <pre>    &lt;number&gt; -1 &lt;/number&gt;</pre>
 * &lt;/line&gt; <br>
 * Пример полного xml - файла: <br>
 * &lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt; <br>
 * &lt;matrix&gt;
 * <pre>    &lt;line&gt;</pre>
 * <pre>        &lt;number&gt; 1 &lt;/number&gt;</pre>
 * <pre>        &lt;number&gt; 2154.47 &lt;/number&gt;</pre>
 * <pre>        &lt;number&gt; -1 &lt;/number&gt;</pre>
 * <pre>    &lt;/line&gt;</pre>
 * <pre>    &lt;line&gt;</pre>
 * <pre>        &lt;number&gt; 2154.47 &lt;/number&gt;</pre>
 * <pre>        &lt;number&gt; 1.0 &lt;/number&gt;</pre>
 * <pre>        &lt;number&gt; 6.0 &lt;/number&gt;</pre>
 * <pre>    &lt;/line&gt;</pre>
 * <pre>    &lt;line&gt;</pre>
 * <pre>        &lt;number&gt; -1,0 &lt;/number&gt;</pre>
 * <pre>        &lt;number&gt; 6.0 &lt;/number&gt;</pre>
 * <pre>        &lt;number&gt; 1.0 &lt;/number&gt;</pre>
 * <pre>    &lt;/line&gt;</pre>
 * &lt;/matrix&gt;
 * @see LineDTO
 */
@Component
@XmlRootElement(name = "matrix")
public class MatrixDTO implements Serializable {
    @XmlTransient
    private static final long serialVersionUID = 1L;

    /**
     * Матрица расстояний.
     */
    @XmlElement(name = "line")
    private ArrayList<LineDTO> matrix;

    /**
     * Конструктор, инициализирует матрицу расстояний.
     */
    public MatrixDTO() {
        matrix = new ArrayList<>();
    }

    /**
     * Функция добавлений строки с набором расстояний.
     * @param lineDTO добовляемая строка
     * @see LineDTO
     */
    public void addLine(LineDTO lineDTO) {
        matrix.add(lineDTO);
    }

    /**
     * Функция получения расстоянии между городами. В качестве параметров принимает id городов.
     * Если расстояние между городами не содержится в матрице расстояний matrix, то возвращает -1 и выдаёт NotDistanceFoundException.
     * Так же если будет произведён выход на массив будет возвращенно значение -1 и выдаётся IndexOutOfBoundsException с сообщение "Нет данных о этих городах".
     * @param from id города от которого начинается рассчёт расстояния
     * @param to id города, к которому необходимо рассчитать расстояние
     * @return возвращает расстояние между городами
     */
    public double getDistance(int from, int to) {
        double distance = -1;
        try {
            distance = this.matrix.get(from - 1).line.get(to - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Нет данных о этих городах");
        }

        if (distance == -1) {
            try {
                throw new NotDistanceFoundException();
            } catch (NotDistanceFoundException e) {
                e.printStackTrace();
            } finally {
                return -1;
            }
        }

        return distance;
    }

    /**
     * Функция вовращения матрицы расстояний.
     * @return ArrayList&lt;LineDTO&gt;
     * @see LineDTO
     */
    public ArrayList<LineDTO> getMatrix() {
        return matrix;
    }

    /**
     * Функция записи матрицы расстояний по указанному пути в xml - файл.
     * Тип список принимаемых в качестве параметров List&lt;LineDTO&gt;.
     * @param matrix матрица расстояний
     */
    public void marshal(List<LineDTO> matrix) {
        MatrixDTO matrixDTO = new MatrixDTO();

        for (LineDTO lineDTO: matrix) {
            LineDTO tmpLineDTO = new LineDTO();
            for (Double number: lineDTO.line) {
                tmpLineDTO.addNumber(number);
            }
            matrixDTO.addLine(tmpLineDTO);
        }

        try {
            JAXBContext context = JAXBContext.newInstance(MatrixDTO.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(matrixDTO, new File("src/main/resources/matrix/matrix.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция чтения матрицы расстояний из указанного xml - файл.
     * @param path путь к xml -  файлу
     */
    public void unmarshal(String path) {
        double[][] distanceMatrix;
        MatrixDTO matrixDTO = new MatrixDTO();

        try {
            JAXBContext context = JAXBContext.newInstance(MatrixDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            matrixDTO = (MatrixDTO) unmarshaller.unmarshal(new FileReader(path));

            for (LineDTO lineDTO: matrixDTO.getMatrix()) {
                LineDTO tmpLineDTO = new LineDTO();
                for (Double number: lineDTO.line) {
                    tmpLineDTO.addNumber(number);
                }
                this.matrix.add(tmpLineDTO);
            }
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Функция сириализует матрицу расстояний в массив байтов.
     * В частости импользуется для сириализации матрицы и последующий записи её в БД.
     * @param matrixDTO матрица расстояний
     * @return массив байтов, содержащий матрицу расстояний
     */
    public byte[] matrixDTOSerialization(MatrixDTO matrixDTO) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(matrixDTO);

            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Функция выполняет дисиарилазию матрицы расстояний.
     * В частности используется при извлечении сириализованной матрицы из БД. Для последующего нахождения расстояний между говодами.
     * @param matrix матрица расстояний представленаая в виде массива байт
     */
    public void matrixDTODiserialization(byte[] matrix) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(matrix);
        MatrixDTO matrixDTODesiarizable;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            matrixDTODesiarizable = (MatrixDTO) objectInputStream.readObject();
            this.matrix.clear();
            for (LineDTO lineDTO: matrixDTODesiarizable.matrix) {
                LineDTO tmpLineDTO = new LineDTO();
                for (Double number: lineDTO.line) {
                    tmpLineDTO.addNumber(number);
                }
                this.matrix.add(tmpLineDTO);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Функция возвращает строковое представление объекта.
     * @return строковое предстваление объекта
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (LineDTO lineDTO: this.matrix) {
            output.append(lineDTO.toString());
        }
        return "Matrix:\n" + output.toString();
    }
}