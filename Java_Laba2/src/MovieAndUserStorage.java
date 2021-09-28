import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MovieAndUserStorage
{

    private final ICallBackWithFileReader _eventListener;

    private Map<Integer, Movie> _movieTable;
    private HashMap<Integer, User> _userMap;

    private Vector<String> _usersFiles;
    private boolean _countK = false;

    private int[] _countsOfGradute = new int[5];

    public class InputMovieInSet implements IInputCallBack
    {
        @Override
        public void lineHandler(String line) {
            String[] data = line.split(",");

            Movie newMovie = new Movie(data[2], data[1], Integer.parseInt(data[0]));
            _movieTable.put(newMovie.id, newMovie);
        }
    }

    public class CountRatingOfUser implements IInputCallBack
    {

        private int nowId = -1;

        @Override
        public void lineHandler(String line) {
            String[] data = line.split(",");
            if(data.length == 1)
            {
                String idStr = data[0].substring(0, data[0].length() - 1);
                int idInt = Integer.parseInt(idStr);

                this.nowId = idInt;
            }
            else
            {
                boolean sho = true;
                while(sho) {
                    try {
                        _userMap.get(Integer.parseInt(data[0])).setUserK(_movieTable.get(nowId).getGraduate());

                        sho = false;
                    }
                    catch (Exception e){
                        sho = true;
                    }
                }
            }
        }
    }

    public class InputUserInSet implements IInputCallBack
    {
        private int nowId;

        @Override
        public void lineHandler(String line) {
            String[] data = line.split(",");
            if(data.length == 1)
            {
                String idStr = data[0].substring(0, data[0].length() - 1);
                int idInt = Integer.parseInt(idStr);

                this.nowId = idInt;

            }
            else
            {
                boolean sho = true;
                while(sho) {
                    try {
                        if (!_userMap.containsKey(Integer.parseInt(data[0]))) {
                            _userMap.put(Integer.parseInt(data[0]), new User(Integer.parseInt(data[0])));
                        }
                        _userMap.get(Integer.parseInt(data[0])).setGraduate(Integer.parseInt(data[1]));
                        _movieTable.get(this.nowId).setGraduate(Integer.parseInt(data[1]));

                        _countsOfGradute[Integer.parseInt(data[1]) - 1]++;
                        sho = false;
                    }
                    catch (Exception e){
                        sho = true;
                    }
                }
            }
        }
    }

    /*************************************
     **********CONSTRUCTORS***************
     *************************************/

    public MovieAndUserStorage()
    {
        this._eventListener = new DefaultCallBack();
        this._init();
    }

    public MovieAndUserStorage(ICallBackWithFileReader errorListener)
    {
        this._eventListener = errorListener;
        this._init();
    }

    /*************************************
     **********PUBLIC METHODS*************
     *************************************/

    public void readMovieFromFile(String fileName)
    {
        FileReaderInOtherProcess f = new FileReaderInOtherProcess(fileName, this._eventListener, new MovieAndUserStorage.InputMovieInSet());

        f.start();
    }

    public void readUserFromFile(String fileName)
    {
        FileReaderInOtherProcess f = new FileReaderInOtherProcess(fileName, this._eventListener, new MovieAndUserStorage.InputUserInSet());
        this._usersFiles.add(fileName);
        f.start();
        this._countK = false;
    }


    /*
    getTheMostFrequentGraduate()
    @params - none
    @ret - the most popular graduate from 1 to 5
     */
    public int getTheMostFrequentGraduate()
    {
        int ans = 0;
        for(int i = 1; i < 5; i++)
        {
            ans = (this._countsOfGradute[ans] >= this._countsOfGradute[i]) ? ans : i;
        }
        return ans + 1;
    }

    /*
    getTheBestFilm()
    @params - none
    @ret - title of the best film
     */
    public String getTheBestFilm()
    {
        String answer = "";
        double answersGraduate = 0;
        int count = 0;
        for(Movie m: this._movieTable.values())
        {
            if(m.getGraduate() > answersGraduate || m.getGraduate() == answersGraduate && count < m.getCountOfGraduate())
            {
                answersGraduate = m.getGraduate();
                answer = m.title;
                count = m.getCountOfGraduate();
            }
        }
        return answer;
    }


    /*
    getTheBestFilm()
    @params - none
    @ret - title of the worst film
     */
    public String getTheWorstFilm()
    {
        String answer = "";
        double answersGraduate = 6;
        int count = 0;
        for(Movie m: this._movieTable.values())
        {
            if(m.getGraduate() < answersGraduate || m.getGraduate() == answersGraduate && count < m.getCountOfGraduate())
            {
                answersGraduate = m.getGraduate();
                answer = m.title;
                count = m.getCountOfGraduate();
            }
        }
        return answer;
    }

    /*
    getTheMostActive()
    @params - none
    @ret - the most active user
     */
    public int getTheMostActive()
    {
        int answer = -1;
        int count = 0;

        for(User u : this._userMap.values())
        {
            answer = (count < u.getCountOfGraduate()) ? u.id : answer;
        }

        return answer;
    }

    public String getTheMostPopularFilm()
    {
        String answer = "";
        int count = -1;

        for(Movie m : this._movieTable.values())
        {
            answer = (count < m.getCountOfGraduate()) ? m.title : answer;
        }

        return answer;
    }

    public int[] getAngryUsers(int count)
    {
        if(!this._countK){
            this._countRating();
        }

        User[] u = new User[count];
        int[] answer = new int[count];
        for(int k = 0 ; k < count; k++) {u[k] = new User(-1);}

        for(User user : this._userMap.values())
        {
            boolean d = true;
            for(int i = count - 1; i >= 0 && d; i--)
            {
                if(user.getUserK() < u[i].getUserK() || i == 0)
                {
                    if(i + 1 != count) {
                        User t = u[i];
                        for (int j = i + 1; j < count; j++) {
                            User t2 = t;
                            t = u[j];
                            u[j] = t2;
                        }
                        u[i] = user;
                    }
                    d = false;
                }
            }
        }

        for(int i = 0; i < count; i++) answer[i] = u[i].id;

        return answer;
    }


    /*************************************
    **********PRIVATE METHODS*************
    *************************************/
    private void _init()
    {
        this._movieTable = new HashMap<Integer, Movie>();
        this._userMap = new HashMap<Integer, User>();
        this._usersFiles = new Vector<String>();
        for(int k : this._countsOfGradute) k = 0;
    }

    private void _countRating()
    {
        FileReaderInOtherProcess[] f = new FileReaderInOtherProcess[this._usersFiles.size()];
        for(int i = 0; i < this._usersFiles.size(); i++)
        {
            f[i] = new FileReaderInOtherProcess(this._usersFiles.get(i), new DefaultCallBack(), new CountRatingOfUser());
            f[i].start();
        }

        boolean allNotDead = true;
        while(allNotDead)
        {
            allNotDead = false;
            for(FileReaderInOtherProcess F : f)
            {
                allNotDead = allNotDead || F.isAlive();
            }
        }
        this._countK = true;
    }

}
