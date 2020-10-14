package cl.dewebsic.marketplacedeventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class IntroductoryActivity extends AppCompatActivity {

    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        timer = new Thread(){

            @Override
            public void run()
            {
                try{
                    synchronized (this){
                        wait(6000);
                    }

                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(IntroductoryActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        timer.start();


    }
}