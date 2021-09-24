public class Kata
{
    public static int[] arrayDiff(int[] a, int[] b)
    {
        StringBuilder str = new StringBuilder("");

        for(int i = 0; i < a.length; i++)
        {
            boolean d = true;
            for(int j = 0; j < b.length; j++)  if(a[i] == b[j]) d = false;

            if(d)
            {
                str.append(a[i]);
                str.append(" ");
            }
        }



        return stringToIntArray(str.toString());
    }

    public static int[] stringToIntArray(String str)
    {
        String[] digits = str.split(" ");
        int[] ans = new int[digits.length];
        if(str.length() > 0) {
            for (int i = 0; i < ans.length; i++) {
                ans[i] = Integer.parseInt(digits[i]);
            }
            return ans;
        }

        return new int[0];
    }

}
