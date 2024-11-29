package com.mtach.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EntryContactActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPhoneNumber;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_contact);

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPhoneNumber = findViewById(R.id.edt_phone);
        btnSubmit = findViewById(R.id.btn_submit);

        Intent intent = getIntent(); //অন্য Activity থেকে পাঠানো ডেটা রিসিভ করে।

        int id = intent.getIntExtra("id", -1);
        if (id != -1) {
            edtName.setText(intent.getStringExtra("name"));
            edtEmail.setText(intent.getStringExtra("email"));
            edtPhoneNumber.setText(intent.getStringExtra("phone"));
            btnSubmit.setText("Update Contact");
        }

        btnSubmit.setOnClickListener(v -> {
            ContactTable ct = new ContactTable(this);
            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();
            String phone = edtPhoneNumber.getText().toString();

            if (id == -1) {
                ct.InsertContact(new ContactModel(name, email, phone));
                Toast.makeText(this, "Contact Added", Toast.LENGTH_SHORT).show();
            } else {
                ContactModel cm = new ContactModel(name, email, phone);
                cm.setId(id);
                ct.UpdateContact(cm);
                Toast.makeText(this, "Contact Updated", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}
