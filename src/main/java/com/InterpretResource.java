package com;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author FedericoL
 */
@Path("/interpret") // The Java class will be hosted at the URI path "/interpret"
public class InterpretResource {

    @GET
    @Produces({"application/json"})
    public String getInterpretation() {
        Gson gson = new Gson();
        Question question = new Question();
        question.text = "Do you drive more than once a week?";
        question.mandatory = true;
        question.attribute = "at_least_weekly";
        return "callback(" + gson.toJson(question) + ")";
    }

    private static class Question {
        private String text;
        private boolean mandatory;
        private String attribute;
    }
}
