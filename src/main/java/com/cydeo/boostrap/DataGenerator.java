package com.cydeo.boostrap;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {

   private final RoleService roleService;
   private final UserService userService;
   private final ProjectService projectService;

   private final TaskService taskService;

    public DataGenerator(RoleService roleService, UserService userService, ProjectService projectService, TaskService taskService) {
        this.roleService = roleService;
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @Override
    public void run(String... args) throws Exception {
        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L,"Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");



        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        UserDTO user1= new UserDTO("Mitch", "Fules","mf@hotmail.com","asd",true,"233",managerRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Jules", "Dirk","jd@ctde.com","wer",true,"34422",adminRole,Gender.FEMALE);
        UserDTO user3 = new UserDTO("Jess","Moork","jm@sad.com","kkf",true,"57543",employeeRole,Gender.FEMALE);


        userService.save(user1);
        userService.save(user2);
        userService.save(user3);


        ProjectDTO project1 = new ProjectDTO("General", "A1", user1, LocalDate.now(),LocalDate.now().plusDays(10),"completed", Status.COMPLETE);
        ProjectDTO project2 = new ProjectDTO("Business", "A2",user2, LocalDate.now(),LocalDate.now().plusDays(10), "on-going", Status.OPEN);
        ProjectDTO project3 = new ProjectDTO("TOEIC","B2", user3, LocalDate.now(), LocalDate.now().plusDays(20), "in progress", Status.IN_PROGRESS);

projectService.save(project1);
projectService.save(project2);
projectService.save(project3);

        TaskDTO task1 = new TaskDTO(1L,project1, user1, "Controller", "Request Mapping", Status.IN_PROGRESS, LocalDate.now().minusDays(4));
        TaskDTO task2 = new TaskDTO(2L,project3, user3, "Configuration", "Database Connection", Status.COMPLETE, LocalDate.now().minusDays(12));
        TaskDTO task3 = new TaskDTO(3L,project3, user2, "Mapping", "One-To-Many", Status.COMPLETE, LocalDate.now().minusDays(8));
        TaskDTO task4 = new TaskDTO(4L,project2, user3, "Dependency Injection", "Autowired", Status.IN_PROGRESS, LocalDate.now().minusDays(20));




    }

}
