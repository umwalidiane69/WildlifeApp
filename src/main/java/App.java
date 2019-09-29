import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {
    public static void main(String[] args){
        staticFileLocation("/public");
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
            model.put("ranger", ranger);
            return new ModelAndView(model, "Allanimals.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
