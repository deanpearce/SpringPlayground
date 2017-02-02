import static spark.Spark.*;
import spark.ModelAndView;

import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");

        // hello.html file is in resources/templates directory
        get("/hey/:name", (req, res) -> {
            Map map = new HashMap();
            map.put("name", req.params(":name"));

            return new ModelAndView(map, "hello");
        }, new ThymeleafTemplateEngine());

        get("/hello/:name", (req, res) -> {
            return "Hello: " + req.params(":name");
        });

        after((request, response) -> {
            response.header("Content-Encoding", "gzip");
        });
    }
}