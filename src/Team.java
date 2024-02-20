package Project00;

import java.util.*;
import Project00.Member;

public abstract class Team
{
    String teamName;
    ArrayList<Member> members = new ArrayList<>();

    public Team(String name)
    {
        teamName = name;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void printTeamMembers() {
        for (int i = 0; i < members.size(); i++)
        {
            System.out.println("\t" + members.get(i).getMemberName());
        }
    }

    public ArrayList<Member> getTeamMembers()
    {
        return members;
    }

    public abstract void createTeamMembers();

}
