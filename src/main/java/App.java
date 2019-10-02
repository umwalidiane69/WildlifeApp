import java.util.HashMap;
import java.util.Map;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args){
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
//        String connectionString = "jdbc:postgresql://ec2-23-21-76-49.compute-1.amazonaws.com:5432/df2ubtmuhc32s7"; //!
//        Sql2o sql2o = new Sql2o(connectionString, "stdhhdzdeynsis", "43a1b82999c0f772dbbd8f7602f0fa50c75b0c3e0f7b0c2caa36637a9569de10");
        get("/", (request, response) -> {
            Map<String, Object>  model= new HashMap();
            return new ModelAndView (model,"Animal_form.hbs");
        }, new HandlebarsTemplateEngine());

        post("mamals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            Endangered animal= new Endangered(name,health,age);
            animal.save();
            model.put("animal", animal);
            model.put("name", name);
            model.put("health", health);
            model.put("age", age);
            return new ModelAndView(model, "Allanimals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/mamal/new", (request, response) -> {
            Map<String, Object>  model= new HashMap();
            return new ModelAndView (model,"Animal2-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("mamal/new", (request, response) -> {
                Map<String, Object> model = new HashMap<String, Object>();
        String name = request.queryParams("name");
           Animal2 anim= new Animal2(name);
           anim.save();
            model.put("anim", anim);
            model.put("name", name);
            return new ModelAndView(model, "Allanimals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sighting/new", (request, response) -> {
            Map<String, Object>  model= new HashMap();
            return new ModelAndView (model,"AllSightings.hbs");
        }, new HandlebarsTemplateEngine());


        post("sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String ranger = request.queryParams("ranger");
            String location = request.queryParams("location");
            Sighting sight = new Sighting(location, ranger);
            sight.save();
            model.put("sight", sight);
            model.put("location",location);
            model.put("ranger", ranger);
            return new ModelAndView(model, "Allanimals.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
