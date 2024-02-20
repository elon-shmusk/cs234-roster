package Project00;

import java.util.*;
import Project00.Team;
import Project00.Member;
import Project00.TeamMember;


public class TeamCool extends Team
{
    public TeamCool(String name)
    {
        super(name);
        createTeamMembers();
    }

    public void createTeamMembers()
    {
        members.add(new TeamMember("Greg Schaper"));
        members.add(new TeamMember("Janice Jones"));
        members.add(new TeamMember("Harold Hero"));
        members.add(new TeamMember("Ali Mohammed"));
        // create a new member class for each team member
        // and add it to the team members list
    }
}
