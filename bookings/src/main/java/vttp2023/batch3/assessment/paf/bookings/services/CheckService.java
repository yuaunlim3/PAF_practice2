package vttp2023.batch3.assessment.paf.bookings.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.repositories.CheckRepository;

@Service
public class CheckService {
    @Autowired private CheckRepository checkRepository;

    public Boolean checkVacancy(int guest, String id){
        System.out.println(">???????????????????THERE IS SPACE");
        return checkRepository.checkVacancy(guest, id);
    }
}
