package ir.parsa2820.terminator;

public class ContextProvider extends android.app.Application {
    private static ContextProvider instance;

    public static ContextProvider getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
