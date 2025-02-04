package vttp2023.batch3.assessment.paf.bookings.Utils;

public class mySQL {
    public static final String getByID = """
            SELECT * FROM acc_occupancy
            WHERE acc_id = ?
            """;
    public static final String updateVacancy = """
            UPDATE acc_occupancy
            SET vacancy = vacancy - ?
            WHERE acc_id = ?
            """;

    public static final String SAVE = """
            INSERT INTO reservations (resv_id, name, email, acc_id, arrival_date,duration)
            VALUES(?,?,?,?,?,?)
            """;
}
