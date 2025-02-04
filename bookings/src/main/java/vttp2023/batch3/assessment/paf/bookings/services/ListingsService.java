package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.models.Accoms;
import vttp2023.batch3.assessment.paf.bookings.models.AccomsInfo;
import vttp2023.batch3.assessment.paf.bookings.models.Bookings;
import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {
	
	@Autowired private ListingsRepository listingsRepository;

	//TODO: Task 2

	public List<String> getCountries(){
		return listingsRepository.getCountries();
	}
	
	//TODO: Task 3

	public List<Accoms> getAccoms(Search search){
		List<Document> result = listingsRepository.getAccoms(search);
		List<Accoms> accoms = new LinkedList<>();

		for(Document document:result){
			accoms.add(Accoms.fromDocument(document));
		}
		return accoms;
	}
	//TODO: Task 4
	public AccomsInfo getInfo(String id){
		Document accDocument = listingsRepository.getInfo(id);

		return AccomsInfo.fromDocument(accDocument);
		
	}

	//TODO: Task 5

	public String save(Bookings bookings, int guest, String id){
		String resv_id = UUID.randomUUID().toString().substring(0, 8);
		System.out.printf(">>>This is the resv_id : %s\n",resv_id);
		System.out.printf(">>>THIS IS the acc_id: %s\n",id);
		listingsRepository.saveBooking(bookings, id, guest);

		return resv_id;
	}


}
