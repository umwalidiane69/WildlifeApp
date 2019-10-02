import org.sql2o.Connection;

import java.util.List;

public class Sighting extends Animal {
    private String location;
    private String ranger;
    public Sighting(String location, String ranger){
        this.location = location;
        this.ranger = ranger;
    }
    public String getLocation() {
        return location;
    }
    public String getRanger() {
        return ranger;
    }  public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sighting (ranger,location) VALUES (:ranger,:location)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("ranger", this.ranger)
                    .addParameter("location", this.location)
//                    .addParameter("id", this.id)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Animal> all() {
        String sql = "SELECT * FROM sighting";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }
}
