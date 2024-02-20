package Project00;
import java.util.*;
import Project00.Team;
import Project00.Member;
import Project00.TeamMember;



public class Team2 extends Team
{
    public Team2(String name)
    {
        super(name);
        createTeamMembers();
    }

    public void createTeamMembers()
    {
        members.add(new TeamMember("Samuel Cadiz"));
        members.add(new TeamMember("Deni Velasquez"));
        members.add(new TeamMember("Kaleb Missmer"));
        members.add(new TeamMember("Brian Demyan"));
        members.add(new TeamMember("Fernando Peralta Castro"));
        // create a new member class for each team member
        // and add it to the team members list
    }
}



