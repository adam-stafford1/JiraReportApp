package com.adam.stafford.rest;

import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

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
        long count = this.issueManager.getIssueCountForProject(pid);
        return Response.ok(new RestResourceModel("Hello World 2" + count)).build();
    }
}