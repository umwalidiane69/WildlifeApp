import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;
public abstract class Animal {
    public String name;
    public int id;
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
//                    .addParameter("id", this.id)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

}
