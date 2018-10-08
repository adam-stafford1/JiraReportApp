package com.adam.stafford.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.ofbiz.core.entity.GenericEntityException;
import com.atlassian.jira.issue.Issue;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.*;


/**
 * A resource of message.
 */
@Named
@Path("/message")
public class RestResource {

    @ComponentImport private IssueManager issueManager;

    @Inject 
    public RestResource(IssueManager issueManager) {
        this.issueManager = issueManager;
    }

    public RestResource() {
    }

    @GET
    @AnonymousAllowed
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getMessage()
    {
        long pid = 10000;
        //long count = this.issueManager.getIssueCountForProject(pid);
        String idsString = "Ids in project: ";
        Collection<Long> issueIds = new ArrayList<Long>();;
        try
        {
            issueIds = issueManager.getIssueIdsForProject(pid);
        }
        catch (final GenericEntityException e)
        {
            //throw new DataAccessException(e);
        }

        Long timeSpent = 0L;
        Long totalTime = 0L;

        for (final Long issueId : issueIds)
        {
            //idsString += ": " + issueId;
            final Issue issue = issueManager.getIssueObject(issueId);
            // We have retrieved all issue ids for the project.
            if (issue != null)
            {
                try
                 {
                    timeSpent += issue.getTimeSpent();
                    totalTime += issue.getEstimate();
                 }
                 catch (final Exception e)
                 {

                 }
            }
            else
            {
                //idsString = "Error";
            }
        }

        String totalTimeString = timeSpent + "/" + totalTime;

        return Response.ok(new RestResourceModel(totalTimeString)).build();
    }
}