package com.adam.stafford.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.ofbiz.core.entity.GenericEntityException;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.bc.project.ProjectService;
import com.atlassian.jira.bc.project.ProjectService.GetProjectResult;
import com.atlassian.jira.bc.project.ProjectResult;

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
    @ComponentImport private ProjectService projectService;

    @Inject 
    public RestResource(IssueManager issueManager, ProjectService projectService) {
        this.issueManager = issueManager;
        this.projectService = projectService;
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
        Collection<Long> issueIds = new ArrayList<Long>();
        try
        {
            ProjectResult project = projectService.getProjectById(pid);
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
        Long hoursSpent = timeSpent / 3600;
        Long hoursTotal = (totalTime / 3600) - hoursSpent;

        return Response.ok(new RestResourceModel(hoursSpent, hoursTotal)).build();
    }
}