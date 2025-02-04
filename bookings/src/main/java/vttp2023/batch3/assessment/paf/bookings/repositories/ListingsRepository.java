package vttp2023.batch3.assessment.paf.bookings.repositories;


import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.assessment.paf.bookings.Utils.Constants;
import vttp2023.batch3.assessment.paf.bookings.Utils.mySQL;
import vttp2023.batch3.assessment.paf.bookings.models.Bookings;
import vttp2023.batch3.assessment.paf.bookings.models.Search;

@Repository
public class ListingsRepository {

	@Autowired private MongoTemplate mongoTemplate;
	@Autowired private JdbcTemplate jdbcTemplate;
	//TODO: Task 2
	/*
	 * db.bookings.distinct("address.country")
	 */
	public List<String> getCountries(){
		List<String> countries =  mongoTemplate.findDistinct(new Query(), Constants.F_COUNTRY,Constants.C_BOOKING,String.class);
		return countries;
	}
	
	//TODO: Task 3
	/*
	 * db.bookings.aggregate([{
    $match:{
        'address.country':{$regex:'Brazil',$options:'i'},
        "guests_included":2,
        "price":{
            $gte:100,
            $lte:1000,
        }
    }
}])

	 */

	 public List<Document> getAccoms(Search search){

		MatchOperation matchOperation = Aggregation.match(
			Criteria.where(Constants.F_COUNTRY).regex(search.getCountry(), "i")
			.and(Constants.F_GUESTS).is(search.getPeople())
			.and(Constants.F_PRICE).gte(search.getMin()).lte(search.getMax())
		);

        Aggregation pipeline= Aggregation.newAggregation(
            matchOperation,
            Aggregation.sort(Sort.by(Sort.Order.asc(Constants.F_PRICE))) 
        );
		AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, Constants.C_BOOKING,Document.class);

		return results.getMappedResults();
				

	 }

	//TODO: Task 4
	public Document getInfo(String id){
		Criteria criteria = Criteria.where(Constants.F_ID).is(id);
		Query query = new Query(criteria);

		return mongoTemplate.find(query, Document.class,Constants.C_BOOKING).get(0);
	}

	//TODO: Task 5

	public void saveBooking(Bookings bookings,String id,int guest){
		jdbcTemplate.update(mySQL.updateVacancy, guest,id);

		jdbcTemplate.update(mySQL.SAVE, bookings.getId(),bookings.getName(),bookings.getEmail(),id,bookings.getDate(),bookings.getStay());
		

	}


}
