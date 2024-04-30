package com.itstep.mythread;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ProgressBar bar = findViewById(R.id.progress_bar);
        bar.setMin(5);
        bar.setMax(50);

        Button btnPause = findViewById(R.id.btn_pause);
        Button btnCancel = findViewById(R.id.btn_cancel);


        try {
            MyBackgroundTask task = new MyBackgroundTask(this, 5,50);

            btnPause.setOnClickListener(new View.OnClickListener() {
                boolean current = false;
                @Override
                public void onClick(View v) {
                    current = ! current;
                    task.setPause(current);
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    task.setCancel(true);
                }
            });

            Thread t = new Thread(task);
            t.start();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}