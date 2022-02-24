package ru.egor.kulizhnyh.distance.calculator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egor.kulizhnyh.distance.calculator.DTO.MatrixDTO;
import ru.egor.kulizhnyh.distance.calculator.exceptions.NotDistanceFoundException;
import ru.egor.kulizhnyh.distance.calculator.models.DistanceMatrix;
import ru.egor.kulizhnyh.distance.calculator.repositorys.DistanceMatrixRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DistanceMatrixService {
    @Autowired
    private DistanceMatrixRepository distanceMatrixRepository;

    public byte[] getMatrixByte() {
        int id = 1;
        DistanceMatrix matrixDAO;
        try {
            Optional<DistanceMatrix> matrixDAOOptional = distanceMatrixRepository.findById(id);
            matrixDAO = matrixDAOOptional.get();
        } catch (NoSuchElementException e) {
            try {
                throw new NotDistanceFoundException();
            } catch (NotDistanceFoundException e1) {
                e1.printStackTrace();
            } finally {
                byte[] emptyArray = new byte[0];
                matrixDAO = new DistanceMatrix();
                matrixDAO.setMatrix(emptyArray);
                return matrixDAO.getMatrix();
            }
        }
        return matrixDAO.getMatrix();
    }

    public void addMatrix(byte[] matrixByte) {
        int id = 1;
        boolean exists = distanceMatrixRepository.existsById(id);
        if (!exists) {
            DistanceMatrix matrixDAO = new DistanceMatrix();
            matrixDAO.setMatrix(matrixByte);

            distanceMatrixRepository.saveAndFlush(matrixDAO);
        }
    }
}
