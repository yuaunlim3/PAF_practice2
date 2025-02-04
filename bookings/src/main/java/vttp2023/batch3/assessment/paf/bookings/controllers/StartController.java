package vttp2023.batch3.assessment.paf.bookings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

@Controller
@RequestMapping({"/","index.html"})
public class StartController {
    
    @GetMapping()
    public ModelAndView goToStart(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/landingpage");
        return mav;
    }

}
