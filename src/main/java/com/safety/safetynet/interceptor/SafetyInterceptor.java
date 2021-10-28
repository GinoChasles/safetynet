package com.safety.safetynet.interceptor;

import com.safety.safetynet.service.FireStationServiceImpl;
import com.safety.safetynet.service.MedicalRecordServiceImpl;
import com.safety.safetynet.service.PersonServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SafetyInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static PersonServiceImpl personServiceImpl;
    private static MedicalRecordServiceImpl medicalRecordServiceImpl;
    private static FireStationServiceImpl fireStationServiceImpl;

    public SafetyInterceptor(PersonServiceImpl personServiceImpl, MedicalRecordServiceImpl medicalRecordServiceImpl, FireStationServiceImpl fireStationServiceImpl) {
        this.personServiceImpl = personServiceImpl;
        this.medicalRecordServiceImpl = medicalRecordServiceImpl;
        this.fireStationServiceImpl = fireStationServiceImpl;
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

//        JsonReaderWriter jsonReaderWriter = new JsonReaderWriter();
//        DataObject dataObject = new DataObject();
//        try {
//            dataObject.setPersons(personServiceImpl.findAll());
//            dataObject.setFirestations(fireStationServiceImpl.findAll());
//            dataObject.setMedicalrecords(medicalRecordServiceImpl.findAll());
//
//            jsonReaderWriter.write(dataObject);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        logger.info("Request and Response is completed");
    }
}
