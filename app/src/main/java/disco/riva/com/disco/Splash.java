package disco.riva.com.disco;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* startActivity(new Intent(Splash.this, Main_Activity_Fragment.class));*/
                startActivity(new Intent(Splash.this, Acceso.class));

                finish();
            }
        }, 1000);
    }

}
