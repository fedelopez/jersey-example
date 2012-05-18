package com.resources;

import com.jaxb.QuestionRecordBean;
import com.sun.jersey.api.json.JSONWithPadding;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import java.util.Arrays;
import java.util.List;

/**
 * @author FedericoL
 */
@Path("/questions")
@Produces({"application/x-javascript", "application/json", "application/xml"})
public class QuestionList {

    @GET
    public JSONWithPadding getInterpretation(@QueryParam("callback") String callback, @QueryParam("type") int type) {
        List<QuestionRecordBean> beanList = Arrays.asList(QuestionRecordBean.createQuestion());
        return new JSONWithPadding(new GenericEntity<List<QuestionRecordBean>>(beanList){}, callback);
    }

}
