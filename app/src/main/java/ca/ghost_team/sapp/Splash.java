package ca.ghost_team.sapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;
import androidx.room.Room;

import com.google.android.material.progressindicator.CircularProgressIndicator;

import ca.ghost_team.sapp.activity.Login;
import ca.ghost_team.sapp.database.sappDatabase;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    private CircularProgressIndicator myProgress;
    int pStatus = 0;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Desactiver le ToolBar
        getSupportActionBar().hide();

        myProgress = findViewById(R.id.myProgressBar);

        new Thread(() -> {
            while (pStatus < 100) {
                pStatus += 1;
                handler.post(() -> myProgress.setProgress(pStatus));

                try {
                    // Just to display the progress slowly
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /* Lorsque la boucle s'arrête, on demarre l'Intent pour lancer l'activité principale */
            if(myProgress.getProgress() == 100){
                // Forcer le demarrage de la Base de données
                sappDatabase db = Room.databaseBuilder(getApplication(),sappDatabase.class,"sappDatabase")
                        .allowMainThreadQueries().build();
                db.annonceDao().start();

                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                finish();
            }
        }).start();
 }

}