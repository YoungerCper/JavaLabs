import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner consoleInput = new Scanner(System.in);
        System.out.println("Input first array: ");
        int[] a = Kata.stringToIntArray(consoleInput.nextLine());

        System.out.println("Input second array: ");
        int[] b = Kata.stringToIntArray(consoleInput.nextLine());

        int[] result = Kata.arrayDiff(a, b);

        for(int i = 0; i < result.length; i++)
        {
            System.out.println(result[i]);
        }
    }

}


