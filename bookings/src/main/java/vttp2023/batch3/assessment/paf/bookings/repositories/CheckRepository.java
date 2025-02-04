package vttp2023.batch3.assessment.paf.bookings.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.assessment.paf.bookings.Utils.mySQL;

@Repository
public class CheckRepository {
    @Autowired private JdbcTemplate jdbcTemplate;

    public Boolean checkVacancy(int guest,String id){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(mySQL.getByID, id);
        while(rs.next()){
            int vacancy = rs.getInt("vacancy");
            if(vacancy >= guest){
                return true;
            }
        }
        return false;
    }
}
