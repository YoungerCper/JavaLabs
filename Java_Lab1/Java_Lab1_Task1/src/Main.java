import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner consoleInput = new Scanner(System.in);

        System.out.println("Input your string, please: ");
        System.out.println(AlphabetWar(consoleInput.nextLine()));
    }

    public static String AlphabetWar(String target)
    {
        String targetLowerCase = target.toLowerCase();
        int scoreCount = 0;

        for(int i = 0; i < targetLowerCase.length(); i++)
        {
            if(targetLowerCase.charAt(i) == 'w') scoreCount += 4;
            if(targetLowerCase.charAt(i) == 'p') scoreCount += 3;
            if(targetLowerCase.charAt(i) == 'b') scoreCount += 2;
            if(targetLowerCase.charAt(i) == 's') scoreCount += 1;

            if(targetLowerCase.charAt(i) == 'm') scoreCount -= 4;
            if(targetLowerCase.charAt(i) == 'q') scoreCount -= 3;
            if(targetLowerCase.charAt(i) == 'd') scoreCount -= 2;
            if(targetLowerCase.charAt(i) == 'z') scoreCount -= 1;
        }
        if(scoreCount > 0) return "Left side wins!";
        if(scoreCount < 0) return "Right side wins!";
        return "Let's fight again!";
    }
}
