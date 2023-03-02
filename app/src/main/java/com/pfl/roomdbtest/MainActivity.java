package com.pfl.roomdbtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.pfl.roomdbtest.databinding.ActivityMainBinding;
import com.pfl.roomdbtest.db.AppDatabase;
import com.pfl.roomdbtest.db.EntityElement;
import com.pfl.roomdbtest.db.MyDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    AppDatabase db;
    MyDao myDao;
    List<EntityElement> entityElements;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db")
                .allowMainThreadQueries()
                .build();
        myDao = db.myDao();
        updateUI();



        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDao.insert(new EntityElement(binding.editText.getText().toString()));
                updateUI();

            }
        });

    }

    private void updateUI() {
        entityElements = myDao.getAll();
        binding.textView.setText("");
        for (EntityElement e : entityElements) {
            binding.textView.append(e.getText() + "\n");
        }
    }

}