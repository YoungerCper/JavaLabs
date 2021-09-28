import java.util.Scanner;

/*
Необходимо разработать код, который отвечает на следующие вопросы:
-->     - Какой фильм имеет больше всех оценок?
-->     - Какой фильм имеет самый высокий и самый низкий рейтинги (если несколько фильмов имеют одинаковый рейтинг, то выигрывает фильм с большим числом оценок)?
-->     - Какой пользователь оставил больше всех оценок (нужен ID)?
-->     - Какую оценку пользователи ставят чаще всего (от 1 до 5)?
-->     - Найдите 10 «злых» пользователей: пользователей, которые чаще всего ставили низкие оценки фильмам с высоким рейтингом.
*/

public class ConsoleInterfaceMovies {

    private MovieAndUserStorage movieAndUserStorage;

    private boolean onWork = true;

    private Scanner consoleInput;

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

        public static final String COUNT = "count";
        public static final String THE_BEST = "best";
        public static final String THE_WORST = "worst";

        public static final String READ_MOVIES = "moviesr";
        public static final String READ_USER = "usersr";

        public static final String MOST_POPULAR_GRADUATE = "pop";
        public static final String MOST_ACTIVE = "active";

        public static final String MOST_POPULAR = "popular";
        public static final String ANGRY = "angry";
    }

    public ConsoleInterfaceMovies()
    {
        this.movieAndUserStorage = new MovieAndUserStorage(new ConsoleErrorCallBack());

        this.consoleInput = new Scanner(System.in);
    }

    public void run()
    {
        String command;

        //this.defaultProcess();
        this.getIntro();
        this.getHelp();
        while(this.onWork)
        {
            command = this.consoleInput.nextLine();

            if(command.equals(Commands.EXIT)) this.exit();
            if(command.equals(Commands.HELP_1)) this.getHelp();
            if(command.equals(Commands.HELP_2)) this.getHelp();
            if(command.equals(Commands.THE_BEST)) this.getBest();
            if(command.equals(Commands.THE_WORST)) this.getWorst();
            if(command.equals(Commands.READ_MOVIES)) this.inputMoviesFromFile();
            if(command.equals(Commands.READ_USER)) this.inputUsersFromFile();
            if(command.equals(Commands.MOST_POPULAR_GRADUATE)) this.getMostPopular();
            if(command.equals(Commands.MOST_ACTIVE)) this.getTheMostActive();
            if(command.equals(Commands.MOST_POPULAR)) this.getMostPopularFilm();
            if(command.equals(Commands.ANGRY)) this.getAngry();
        }
    }

    private void getHelp()
    {
        System.out.println("###Список команд:");
        System.out.println( Commands.EXIT + " - завершение работы программы");
        System.out.println( Commands.HELP_1 + " or " + Commands.HELP_2 + " - показать список команд");
        System.out.println( Commands.MOST_POPULAR + " - фильм с большим количеством оценок");
        System.out.println( Commands.THE_BEST + " - фильм с самой высокой оценкой");
        System.out.println( Commands.THE_WORST + " - фильм с самой низкой оценкой");
        System.out.println( Commands.READ_MOVIES + " - прочитать фильмы с файла");
        System.out.println( Commands.MOST_POPULAR_GRADUATE + " - самая популярнаыя оценка среди пользователей");
        System.out.println( Commands.READ_USER + " - прочитать пользователей с файла");
        System.out.println( Commands.MOST_ACTIVE + " - самый активный пользователь");
        System.out.println( Commands.ANGRY + " - 10 злых пользователей");
    }

    private void inputMoviesFromFile()
    {
        System.out.println(Colors.ANSI_YELLOW + "REQUEST: Введите адрес и имя файла для чтения" + Colors.ANSI_RESET);
        this.movieAndUserStorage.readMovieFromFile(this.consoleInput.nextLine());
    }

    private void inputUsersFromFile()
    {
        System.out.println(Colors.ANSI_YELLOW + "REQUEST: Введите адрес и имя файла для чтения" + Colors.ANSI_RESET);
        this.movieAndUserStorage.readUserFromFile(this.consoleInput.nextLine());
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

    private void getBest()
    {
        System.out.println(this.movieAndUserStorage.getTheBestFilm() + " - самая лучший фильм по мнению пользователей");
    }

    private void getWorst()
    {
        System.out.println(this.movieAndUserStorage.getTheWorstFilm() + " - самая худший фильм по мнению пользователей");
    }

    private void getMostPopular()
    {
        System.out.println(this.movieAndUserStorage.getTheMostFrequentGraduate() + " - самая популярная оценка среди пользователей");
    }

    private void getTheMostActive()
    {
        System.out.println(this.movieAndUserStorage.getTheMostActive() + " - самый активный пользователь");
    }

    private void getMostPopularFilm()
    {
        System.out.println(this.movieAndUserStorage.getTheMostPopularFilm() + " - фильм с самым большим количеством оценок");
    }

    private void getAngry()
    {
        int[] outputs = this.movieAndUserStorage.getAngryUsers(10);
        for(int i : outputs) {System.out.println(i);}
    }

    /*//для того чтобы побыстее проверять
    private void defaultProcess()
    {
        this.movieAndUserStorage.readMovieFromFile("F:\\MoviesData\\movie_titles.csv");
        System.out.println("lkdjfklgdlkfjglkdfg");
        this.consoleInput.nextLine();


        this.movieAndUserStorage.readUserFromFile("F:\\MoviesData\\combined_data_1.txt");
        this.movieAndUserStorage.readUserFromFile("F:\\MoviesData\\combined_data_2.txt");
        this.movieAndUserStorage.readUserFromFile("F:\\MoviesData\\combined_data_3.txt");
        this.movieAndUserStorage.readUserFromFile("F:\\MoviesData\\combined_data_4.txt");
    }*/

}
