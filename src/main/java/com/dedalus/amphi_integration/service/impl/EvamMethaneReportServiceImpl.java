package com.dedalus.amphi_integration.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.dedalus.amphi_integration.classes.LocalDateTimeDeserializer;
import com.dedalus.amphi_integration.dto.EvamMethaneReportRequestDTO;
import com.dedalus.amphi_integration.model.amphi.MethaneReport;
import com.dedalus.amphi_integration.repository.EvamMethaneReportRepository;
import com.dedalus.amphi_integration.service.EvamMethaneReportService;

@Service
public class EvamMethaneReportServiceImpl implements EvamMethaneReportService {

    @Autowired
    EvamMethaneReportRepository evamMethaneReportRepository;

    @Override
    public MethaneReport updateMethaneReport(EvamMethaneReportRequestDTO evamMethaneReportRequestDTO) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().disableHtmlEscaping().create();


        MethaneReport methaneReport = gson.fromJson(evamMethaneReportRequestDTO.getMethaneReport(), MethaneReport.class);

        System.out.println("JSON: " + evamMethaneReportRequestDTO.getMethaneReport());
        System.out.println("Objekt: " + methaneReport);

        Optional<MethaneReport> existingMethaneReport = evamMethaneReportRepository.findById("1");

        if (existingMethaneReport.isEmpty()) {
            methaneReport.setId("1");
            evamMethaneReportRepository.save(methaneReport);
        } else {
            existingMethaneReport.get().setAccess_road(methaneReport.getAccess_road());
            existingMethaneReport.get().setCreated(methaneReport.getCreated());
            existingMethaneReport.get().setExact_location(methaneReport.getExact_location());
            existingMethaneReport.get().setExtra_resources(methaneReport.getExtra_resources());
            existingMethaneReport.get().setHazards(methaneReport.getHazards());
            existingMethaneReport.get().setInventory_level(methaneReport.getInventory_level());
            existingMethaneReport.get().setLast_updated(methaneReport.getLast_updated());
            existingMethaneReport.get().setMajor_incident(methaneReport.getMajor_incident());
            existingMethaneReport.get().setNumbers_affected_green(methaneReport.getNumbers_affected_green());
            existingMethaneReport.get().setNumbers_affected_red(methaneReport.getNumbers_affected_red());
            existingMethaneReport.get().setNumbers_affected_yellow(methaneReport.getNumbers_affected_yellow());
            existingMethaneReport.get().setPosition(methaneReport.getPosition());
            existingMethaneReport.get().setSpecial_injuries(methaneReport.getSpecial_injuries());
            existingMethaneReport.get().setTime_first_departure(methaneReport.getTime_first_departure());
            existingMethaneReport.get().setTypes(methaneReport.getTypes());
            evamMethaneReportRepository.save(existingMethaneReport.get());
            System.out.println("Methane Report Updated");
        }

        return methaneReport;
    }

    @Override
    public MethaneReport getById(String id) {
        return evamMethaneReportRepository.findById(id).orElseThrow(() -> new RuntimeException("No TripLocationHistory found for id: %s".formatted(id)));
    }

    @Override
    public List<MethaneReport> getAll() {
        return evamMethaneReportRepository.findAll();
    }

}
