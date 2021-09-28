
public class User {

    public final int id;
    private int countOfGraduate = 0;
    private int summOfGraduate = 0;


    private double userK = 0;

    public User(int id)
    {
        this.id = id;
    }

    public void setGraduate(int g)
    {
        this.summOfGraduate += g;
        this.countOfGraduate++;
    }

    public void setUserK(double k)
    {
        if(this.summOfGraduate != 0) {
            this.userK += (k / this.summOfGraduate);
        }
        else{
            this.userK = 6;
        }
    }

    public int getCountOfGraduate() {return this.countOfGraduate;}

    public double getUserK() {return this.userK;}

}
