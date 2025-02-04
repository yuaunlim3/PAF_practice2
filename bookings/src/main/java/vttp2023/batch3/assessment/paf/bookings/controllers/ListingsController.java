package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.assessment.paf.bookings.models.Accoms;
import vttp2023.batch3.assessment.paf.bookings.models.AccomsInfo;
import vttp2023.batch3.assessment.paf.bookings.models.Bookings;
import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.paf.bookings.services.CheckService;
import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

@Controller
@RequestMapping()
public class ListingsController {

	@Autowired private ListingsService listingsService;

	@Autowired private CheckService checkService;
	//TODO: Task 2

	@GetMapping("/landingpage")
    public ModelAndView start(){
        ModelAndView mav = new ModelAndView("landingpage");
        mav.addObject("countries",listingsService.getCountries());
        mav.addObject("search", new Search());
        return mav;
    }
	
	//TODO: Task 3

	@GetMapping("/search")
	public ModelAndView search(@Valid @ModelAttribute("search") Search search, BindingResult binding,HttpSession session){
		ModelAndView mav = new ModelAndView();

		if(binding.hasErrors()){
			mav.setViewName("landingpage");
			mav.addObject("countries",listingsService.getCountries());
			return mav;
		}
		
		List<Accoms> accoms = listingsService.getAccoms(search);
		session.setAttribute("search", search);
		mav.addObject("accoms", accoms);
		mav.addObject("search",search);
		mav.setViewName("listingpage");


		return mav;

	}


	//TODO: Task 4
	@GetMapping("/getDetails/{id}")
	public ModelAndView getDetails(@PathVariable("id") String id,HttpSession session){
		ModelAndView mav = new ModelAndView("booking");
		AccomsInfo info = listingsService.getInfo(id);
		mav.addObject("booking",new Bookings());
		mav.addObject("info", info);
		session.setAttribute("id", id);
		mav.addObject("id", id);
		return mav;
	}

	//TODO: Task 5
	@GetMapping("/book")
	public ModelAndView book(@Valid @ModelAttribute("booking")Bookings bookings,BindingResult result, HttpSession session){
		ModelAndView mav = new ModelAndView("success");
		String id = (String) session.getAttribute("id");
		Search search = (Search) session.getAttribute("search");
		int guest = search.getPeople();

		if(result.hasErrors()){
			System.out.println(result.getAllErrors());
			AccomsInfo info = listingsService.getInfo(id);
			mav.setViewName("booking");
			mav.addObject("info", info);
			mav.addObject("id", id);
			return mav;
		}



		//Check vacancy
		Boolean available = checkService.checkVacancy(guest, id);
		if(!available){
			AccomsInfo info = listingsService.getInfo(id);
			mav.addObject("booking",new Bookings());
			mav.setViewName("booking");
			mav.addObject("info", info);
			mav.addObject("id", id);
			mav.addObject("error", "Room is not available");

			return mav;

		}
		else{
			String resv_id = listingsService.save(bookings, guest, id);
		
			mav.addObject("id", resv_id);
	
			mav.setViewName("success");
			return mav;
		}
	}


}
