import java.util.Scanner;

public class ConsoleInterfaceMovies {

    private MovieStorage movieStorage;
    private UserStorage userStorage;

    private boolean onWork = true;

    public class ConsoleErrorCallBack implements ICallBackWithFileReader
    {
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_RESET = "\u001B[0m";

        public void getError()
        {
            System.out.println(ANSI_RED + "ERROR: При чтении файла произошла какая-то ошибка, возможно, его не существует." + ANSI_RESET);
        }

        public void getSuccessful()
        {
            System.out.println(ANSI_GREEN + "SUCCESSFUL: Файл прочтён успешно." + ANSI_RESET);
        }
    }

    public static class Colors
    {
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";
    }

    public static class Commands
    {
        public static final String EXIT = "exit";
        public static final String HELP_1 = "help";
        public static final String HELP_2 = "?";
    }

    public ConsoleInterfaceMovies()
    {
        this.movieStorage = new MovieStorage(new ConsoleErrorCallBack());
        this.userStorage = new UserStorage(new ConsoleErrorCallBack());
    }

    public void run()
    {
        String command;
        Scanner consoleInput = new Scanner(System.in);

        this.getIntro();
        this.getHelp();
        while(this.onWork)
        {
            command = consoleInput.nextLine();

            if(command.equals(Commands.EXIT)) this.exit();
        }
    }

    private void getHelp()
    {
        System.out.println("###Список команд:");
        System.out.println( Commands.EXIT + " - завершение работы программы");
        System.out.println( Commands.HELP_1 + " or " + Commands.HELP_2 + " - показать список команд");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    private void inputMoviesFromFile()
    {
        System.out.println(Colors.ANSI_YELLOW + "REQUEST: Введите адрес и имя файла для чтения" + Colors.ANSI_RESET);
        this.movieStorage.readFromFile("F:/");
    }

    private void inputUsersFromFile()
    {
        System.out.println(Colors.ANSI_YELLOW + "REQUEST: Введите адрес и имя файла для чтения" + Colors.ANSI_RESET);

    }

    private void exit(){this.onWork = false;}

    private void getIntro()
    {
        System.out.println(Colors.ANSI_PURPLE + "█╗────████╗─███╗───████╗─████╗" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_PURPLE + "█║────█╔═█║─█╔═█╗──█╔═█║─╚══█║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_PURPLE + "█║────████║─████║──████║─████║" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_PURPLE + "█║────█╔═█║─█╔══█╗─█╔═█║─█╔══╝" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_PURPLE + "████╗─█║─█║─████╔╝─█║─█║─████╗" + Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_PURPLE + "╚═══╝─╚╝─╚╝─╚═══╝──╚╝─╚╝─╚═══╝" + Colors.ANSI_RESET);
    }
}
