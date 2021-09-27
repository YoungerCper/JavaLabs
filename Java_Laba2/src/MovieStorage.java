import java.util.HashSet;

public class MovieStorage
{

    private final ICallBackWithFileReader _eventListener;
    private HashSet<Movie> _movieTable;

    public class InputMovieInSet implements IInputCallBack
    {
        @Override
        public void lineHandler(String line) {
            String[] data = line.split(",");

            Movie newMovie = new Movie(data[2], Integer.parseInt(data[1]), Integer.parseInt(data[0]));
            _movieTable.add(newMovie);
        }
    }

    /*************************************
     **********CONSTRUCTORS***************
     *************************************/

    public MovieStorage()
    {
        this._eventListener = new DefaultCallBack();
        this._init();
    }

    public MovieStorage(ICallBackWithFileReader errorListener)
    {
        this._eventListener = errorListener;
        this._init();
    }

    /*************************************
     **********PUBLIC METHODS*************
     *************************************/

    public void readFromFile(String fileName)
    {
        FileReaderInOtherProcess f = new FileReaderInOtherProcess(fileName, this._eventListener, new InputMovieInSet());

        f.start();
    }

    /*************************************
    **********PRIVATE METHODS*************
    *************************************/
    private void _init()
    {
        this._movieTable = new HashSet<Movie>();
    }

}
