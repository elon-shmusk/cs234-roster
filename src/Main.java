/*
Purpose: Use GitHub and learn to fork, clone, modify, commit, push, pull request
         a project repository.
         Learn the sequential nature and necessary communication between team members
         using GitHub as a shared software repository
Author of initial code base: Greg Schaper
Authors of modified code base: <Put Team Member Names Here>

TODO: Modify this software so that your team will be added to the course with each team member's
      name being output after the team name is output.
      This will require each team member to clone a forked repository (by one of the team members)
      add their code to add their name to the team and save the changes to the shared repository.
      Team names will be Team1, Team2, Team3, Team4 ... etc. as assigned in class.
 */

package Project00;

import java.util.*;
import Project00.TeamCool;
import Project00.Member;

public class Main {


    public static void main(String[] args)
    {
        Course course = new Course();
		course.createTeams();
		course.printCourseMembers();
    }
}
