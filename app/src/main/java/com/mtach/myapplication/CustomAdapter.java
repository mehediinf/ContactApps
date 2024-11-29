package com.mtach.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<ContactModel> allContacts;
    Context mContext;

    public CustomAdapter(ArrayList<ContactModel> allContacts, Context mContext) {
        this.allContacts = allContacts;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return allContacts.size();
    }

    @Override
    public Object getItem(int position) {
        return allContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
        }

        ContactModel cm = allContacts.get(position);

        TextView txtName = convertView.findViewById(R.id.txt_name);
        TextView txtEmail = convertView.findViewById(R.id.txt_email);
        TextView txtPhone = convertView.findViewById(R.id.txt_phone);
        Button btnUpdate = convertView.findViewById(R.id.btn_update);
        Button btnDelete = convertView.findViewById(R.id.btn_delete);

        txtName.setText(cm.getName());
        txtEmail.setText(cm.getEmail());
        txtPhone.setText(cm.getPhoneNumber());

        btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, EntryContactActivity.class);
            intent.putExtra("id", cm.getId());
            intent.putExtra("name", cm.getName());
            intent.putExtra("email", cm.getEmail());
            intent.putExtra("phone", cm.getPhoneNumber());
            mContext.startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            ContactTable ct = new ContactTable(mContext);
            ct.DeleteContact(cm.getId());
            allContacts.remove(position);
            notifyDataSetChanged();
            Toast.makeText(mContext, "Contact Deleted", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
