package com.example.Project3.services;

import com.example.Project3.models.Measurement;
import com.example.Project3.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurmentsService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurmentsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    public void addMeasurment(Measurement measurement) {
        measurementsRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement) {
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementDateTime(LocalDateTime.now());
    }
}
