package disco.riva.com.disco;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class SimpleBlogAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
