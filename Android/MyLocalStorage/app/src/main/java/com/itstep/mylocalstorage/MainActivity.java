package com.itstep.mylocalstorage;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itstep.mylocalstorage.models.EntityModel;
import com.itstep.mylocalstorage.repositories.EntityRepository;
import com.itstep.mylocalstorage.services.DbService;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.AbstractList;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    String FILE_NAME = "myText.txt";



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

        DbService.getInstance(this);

        try {
            EntityRepository rep = EntityRepository.getInstance();
            EntityModel model = EntityRepository.createModel();
            model.setId(10);
            model.setName("Hello World");
            rep.create(model);

            rep.readAllFromNet(new Callback<ArrayList<EntityModel>>() {
                @Override
                public void onResponse(Call<ArrayList<EntityModel>> call, Response<ArrayList<EntityModel>> response) {
                    if (response.isSuccessful()) {
                        ArrayList<EntityModel> entityModels =  new ArrayList<>();
                        entityModels.addAll(response.body());
                        for (int i = 0; i < entityModels.size(); i++) {
                            Log.d("keeper", entityModels.get(i).getName());
                            // findViewById();
                        }
                    } else {
                        // Обработка ошибки
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<EntityModel>> call, Throwable t) {
                    // Обработка ошибки
                }
            });

            EntityModel modelById = rep.getById(10);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        inputText = findViewById(R.id.inputText);
        outputText = findViewById(R.id.outputText);

        readText();

        ((Button) findViewById(R.id.btnUpdate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText();
                readText();
            }
        });

    }


    EditText inputText;
    TextView outputText;

    protected void saveText() {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            String txt = String.valueOf(inputText.getText());

            fos.write(txt.getBytes());
            toast("File Save");
        }catch (Exception e) {
            toast(e.getMessage());
        }
    }


    protected void readText() {
        try (FileInputStream fis = openFileInput(FILE_NAME)){
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String txt = new String(buffer);
            outputText.setText(txt);
        }catch (Exception e) {
            toast(e.getMessage());
        }
    }


    protected void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}