package view;

import java.util.Scanner;

public class MainMenu {
    public void run(Scanner scanner){
        String command=scanner.nextLine();
        MainMenuController controller = new MainMenuController();
        // Simulate user input
        controller.handleCommand(command);

    }
}
