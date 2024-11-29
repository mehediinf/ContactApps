package com.mtach.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView contactListView;

    Button btn_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListView = findViewById(R.id.contact_list);
        btn_List = findViewById(R.id.btn_showListView);

        btn_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,EntryContactActivity.class);
                startActivity(intent);

            }
        });

        loadContacts();
    }

    private void loadContacts() {
        ContactTable ct = new ContactTable(this);
        ArrayList<ContactModel> allContacts = ct.ReadContacts();

        CustomAdapter adapter = new CustomAdapter(allContacts, this);
        contactListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
    }
}
