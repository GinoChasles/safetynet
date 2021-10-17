package com.safety.safetynet.interceptor;

import com.safety.safetynet.data.DataObject;
import com.safety.safetynet.data.JsonReaderWriter;
import com.safety.safetynet.model.FireStation;
import com.safety.safetynet.repository.PersonRepository;
import com.safety.safetynet.service.FireStationService;
import com.safety.safetynet.service.MedicalRecordService;
import com.safety.safetynet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SafetyInterceptor implements HandlerInterceptor {
//    private static Logger logger = LoggerFactory.getLogger(this.getClass());
    private static PersonService personService;
    private static MedicalRecordService medicalRecordService;
    private static FireStationService fireStationService;

    public SafetyInterceptor(PersonService personService, MedicalRecordService medicalRecordService, FireStationService fireStationService) {
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
        this.fireStationService = fireStationService;
    }

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("Pre Handle method is Calling");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

//        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        JsonReaderWriter jsonReaderWriter = new JsonReaderWriter();



        DataObject dataObject = new DataObject();
        try {
            dataObject.setPersons(personService.findAll());
            dataObject.setFirestations(fireStationService.findAll());
            dataObject.setMedicalrecords(medicalRecordService.findAll());

            jsonReaderWriter.write(dataObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
                System.out.println("after Handle method is Calling");

//        logger.info("Request and Response is completed");
    }
}
