package com;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author FedericoL
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/interpret")
public class InterpretResource {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces({"application/json"})
    public String getInterpretation() {
        Gson gson = new Gson();
        Question question = new Question();
        question.attribute = "at_least_weekly";
        question.mandatory = true;
        question.text = "Do you drive more than once a week?";
        gson.toJson(question);
        return gson.toJson(question);
    }

    private static class Question {
        private String text;
        private boolean mandatory;
        private String attribute;
    }
}
