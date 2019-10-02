import java.util.List;
import org.sql2o.*;
public class Endangered extends Animal{
    private String health;
    private String age;
    public Endangered( String name, String health, String age) {
        this.name = name;
        this.health = health;
        this.age = age;
    }

    public String getHealth(){
        return health;
    }
    public String getAge(){
        return age;
    }
    public String getName() {
        return name;
    }
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,health,age) VALUES (:name,:health,:age)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
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
