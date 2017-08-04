package co.progredi.contactosmovil;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lstContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstContactos = (ListView) findViewById(R.id.lstContactos);
        leerContactos();
//        Intent intentContactos = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
//        startActivity(intentContactos);
    }

    private void leerContactos() {
        List<String> listaContactos = new ArrayList<>();
        try {
            Uri contentUri = ContactsContract.Data.CONTENT_URI;
            //Nos permite acceder a un proveedor existente.
            Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
            if (!cursor.moveToFirst()) {
                return;
            }
            do {
                listaContactos.add(cursor.getString(cursor.getColumnIndex("data1")));
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listaContactos);
        lstContactos.setAdapter(adaptador);
    }

}
