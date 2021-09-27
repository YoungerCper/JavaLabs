

public class Movie {

    public final String title;
    public final int year;
    public final int id;

    private double graduate = 0;
    private int countOfGraduate = 0;

    public Movie(String title, int year, int id)
    {
        this.title = title;
        this.id = id;
        this.year = year;
    }

    public void setGraduate(int graduate)
    {
        this.graduate = (this.graduate * this.countOfGraduate + graduate) / ++this.countOfGraduate;
    }

    public double getGraduate(){ return this.graduate;}
    public int getCountOfGraduate(){return this.countOfGraduate;}

}
