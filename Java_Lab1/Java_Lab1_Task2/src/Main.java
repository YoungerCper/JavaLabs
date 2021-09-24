import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner consoleInput = new Scanner(System.in);
        System.out.println("Input your string please: ");
        System.out.println(Shifter.count(consoleInput.nextLine()));
    }
}

/*
Это единственный раз когда я создал класс не в отдельном файле, простите я больше так не буду
 */

class Shifter{

    public static int count(String target)
    {
        String[] words = target.split(" ");
        int answer = 0;
        for(int i = 0; i < words.length; i++)
        {
            if(itShifter(words[i]) && itFirstTime(words[i], words, i)) answer++;
        }

        return answer;
    }

    public static boolean itShifter(String word)
    {
        String needLitters = "HINOSXZMW";

        if(word.length() == 0) return false;

        for(int i = 0 ; i < word.length(); i++)
        {
            if(needLitters.indexOf(word.charAt(i)) < 0) return false;
        }
        return true;
    }

    public static boolean itFirstTime(String word, String[] words, int index)
    {
        for(int i = 0; i < index; i++)
        {
            if(word.equals(words[i])) return false;
        }
        return true;
    }
}
