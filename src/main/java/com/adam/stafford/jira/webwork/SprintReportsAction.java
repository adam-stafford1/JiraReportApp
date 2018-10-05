package com.adam.stafford.jira.webwork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.web.action.JiraWebActionSupport;

public class SprintReportsAction extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(SprintReportsAction.class);

    @Override
    public String execute() throws Exception {
        //return "Sprint Reports";
        return super.execute(); //returns SUCCESS
    }
}
