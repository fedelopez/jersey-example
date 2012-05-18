package com;

import com.google.gson.Gson;
import com.sun.jersey.api.json.JSONWithPadding;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import java.util.List;

/**
 * @author FedericoL
 */
@Path("/interpret") // The Java class will be hosted at the URI path "/interpret"
@Produces({"application/x-javascript", "application/json", "application/xml"})
public class Interpreter {

    @GET
    public JSONWithPadding getInterpretation(@QueryParam("callback") String callback, @QueryParam("type") int type) {
        return new JSONWithPadding(new GenericEntity<Question>(createQuestion()) { }, callback);
    }

    private static class Question {

        private String text;
        private boolean mandatory;
        private String attribute;
    }

    private Question createQuestion() {
        Question question = new Question();
        question.text = "Do you drive more than once a week?";
        question.mandatory = true;
        question.attribute = "at_least_weekly";
        return question;
    }
}
