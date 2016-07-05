package com.khatrigmail.kunal15.recyclerview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MessageModel> arr = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    Row r;
    Toolbar t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        t = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(t);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        insertDummyContactWrapper();
        arr.add(new MessageModel("Kunal", "8980532889"));
        r = new Row(arr, getApplicationContext());
        recyclerView.setAdapter(r);
    }

    public void readContacts(){
        Cursor contacts = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while(contacts.moveToNext()) {
            int index = contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String name = contacts.getString(index);
            String number = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            arr.add(new MessageModel(name,number));
        }
        contacts.close();
    }
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    private void insertDummyContactWrapper() {
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_CONTACTS);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.READ_CONTACTS},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        readContacts();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    readContacts();
                } else {
                    // Permission Denied
                    Toast.makeText(MainActivity.this, "WRITE_CONTACTS Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}

