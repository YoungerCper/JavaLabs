public class UserStorage
{


    private ICallBackWithFileReader _errorListener;

    public UserStorage(ICallBackWithFileReader errorListener)
    {
        this._errorListener = errorListener;
    }

    public UserStorage()
    {
        this._errorListener = new DefaultCallBack();
    }
}
