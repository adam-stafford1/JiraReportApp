package com.adam.stafford.jira.webwork;

import com.atlassian.webresource.api.assembler.PageBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.web.action.JiraWebActionSupport;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SprintReportsAction extends JiraWebActionSupport
{
    @Inject 
    private PageBuilderService pageBuilderService;

    private static final Logger log = LoggerFactory.getLogger(SprintReportsAction.class);

    @Override
    public String execute() throws Exception {
        //return "Sprint Reports";
        return super.execute(); //returns SUCCESS
    }

    public void setPageBuilderService(PageBuilderService pageBuilderService) { 
        this.pageBuilderService = pageBuilderService;
    }
}
