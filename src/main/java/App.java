import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/heroes/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.clearAllHeroes();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete an individual task
        get("/heroes/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToDelete = Integer.parseInt(request.params("id")); //pull id - must match route segment
            Hero deleteHero = Hero.findById(idOfTaskToDelete); //use it to find task
            deleteHero.deleteHero();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show all tasks
        get("/index", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new task form
        get("/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form
        post("/heroes", (request, response) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age=Integer.parseInt(request.queryParams("age"));
            String specialPower = request.queryParams("specialPower");
            String weakness=request.queryParams("weakness");
            Hero newHero = new Hero(name,age,specialPower,weakness);
            response.redirect("/index");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual task
        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToFind = Integer.parseInt(request.params("id")); //pull id - must match route segment
            Hero foundHero = Hero.findById(idOfTaskToFind); //use it to find task
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "hero-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a task
        get("/heroes/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTaskToEdit = Integer.parseInt(request.params("id"));
            Hero editHero = Hero.findById(idOfTaskToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process a form to update a task
        post("/heroes/:id", (request, response) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            int newAge=Integer.parseInt(request.queryParams("age"));
            String newSpecialPower = request.queryParams("specialPower");
            String newWeakness=request.queryParams("weakness");
            int idOfTaskToEdit = Integer.parseInt(request.params("id"));
            Hero editHero = Hero.findById(idOfTaskToEdit);
            editHero.update(newName,newAge,newSpecialPower,newWeakness);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/squads/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.clearAllSquads();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete an individual task
        get("/squads/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(request.params("id")); //pull id - must match route segment
            Squad deleteHero = Squad.findById(idOfSquadToDelete); //use it to find task
            deleteHero.deleteSquad();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show all tasks
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "index22.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new task form
        get("/squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process new task form
        post("/squads", (request, response) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int size=Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");

            Squad newSquad = new Squad(name,cause,size);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual task
        get("/squads/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(request.params("id")); //pull id - must match route segment
            Squad foundSquad = Squad.findById(idOfSquadToFind); //use it to find task
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squad-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a task
        get("/squads/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(request.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            model.put("editSquad", editSquad);
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());

        //task: process a form to update a task
        post("/squads/:id", (request, response) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            int newSize=Integer.parseInt(request.queryParams("size"));
            String newCause=request.queryParams("cause");
            int idOfSquadToEdit = Integer.parseInt(request.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            editSquad.update(newName,newCause,newSize);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());






    }
}
