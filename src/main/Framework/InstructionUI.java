package main.Framework;

public class InstructionUI {

    public void run(){
        System.out.println("\n");
        System.out.println("General Instruction:");
        System.out.println("Level 0 is the highest authority level, 9 is the lower authority level.");
        System.out.println("A person can only create/delete an employee who has lower authority level. For example, level 0 can create/delete a level 1 employee");
        System.out.println(("A person can only create/delete a work which has lower authority level. For example, level 0 can create/delete a level 1 work"));
        System.out.println("A person can only be involved in a work that has a equal or lower authority level");
        System.out.println("\n \n ");
    }
}
