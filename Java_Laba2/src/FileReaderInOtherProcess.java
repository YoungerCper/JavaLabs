import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderInOtherProcess extends Thread {

    private final String _nameFile;
    private FileReader _file;

    private final ICallBackWithFileReader _errorListener;
    private final IInputCallBack _lineHandler;

    public class DefaultLineHandler implements IInputCallBack
    {
        public void lineHandler(String line){}
    }

    public FileReaderInOtherProcess(String nameFile, ICallBackWithFileReader errorListener)
    {
        this._lineHandler = new DefaultLineHandler();
        this._nameFile = nameFile;
        this._errorListener = errorListener;
    }

    public FileReaderInOtherProcess(String nameFile, ICallBackWithFileReader errorListener, IInputCallBack lineHandler)
    {
        this._lineHandler = lineHandler;
        this._nameFile = nameFile;
        this._errorListener = errorListener;
    }

    public FileReaderInOtherProcess(String nameFile)
    {
        this._lineHandler = new DefaultLineHandler();
        this._nameFile = nameFile;
        this._errorListener = new DefaultCallBack();
    }

    @Override
    public void run()
    {
        if(!this._openFile())
        {
            this._errorListener.getError();
            return;
        }

        try {
            this._readFromFile();
        } catch (IOException e) {
            this._errorListener.getError();
        }

        if(!this._closeFile())
        {
            this._errorListener.getError();
            return;
        }

        this._errorListener.getSuccessful();
    }

    private void _readFromFile() throws IOException {

        BufferedReader reader = new BufferedReader(this._file);

        String line = reader.readLine();
        while(line != null)
        {
            this._lineHandler.lineHandler(line);
            line = reader.readLine();
        }
    }

    private boolean _openFile()
    {
        try {
            this._file = new FileReader(this._nameFile);
            return true;
        }
        catch (FileNotFoundException e) {
            this._file = null;
            return false;
        }
    }

    private boolean _closeFile()
    {
        if(this._file == null) return false;
        try {
            this._file.close();
            return true;
        }
        catch (IOException e) {

            return false;
        }
    }
}
