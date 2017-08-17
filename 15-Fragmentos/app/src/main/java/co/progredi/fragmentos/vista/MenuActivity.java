package co.progredi.fragmentos.vista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import co.progredi.fragmentos.R;
import co.progredi.fragmentos.vista.fragmento.FragmentoRegistrarProducto;
import co.progredi.fragmentos.vista.fragmento.FragmentoRegistrarUsuario;
import co.progredi.fragmentos.vista.fragmento.GenericoFragmento;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frmContenido;
    private GenericoFragmento genericoFragmento;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        frmContenido = (FrameLayout) findViewById(R.id.frmContenido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                if (genericoFragmento == null) {
                    return;
                }
                genericoFragmento.click(view);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        Menu menu = navigationView.getMenu();
//        for (int i = 0; i < 10; i++) {
//            menu.add("Listo el pollo " + i);
//        }
        navigationView.setNavigationItemSelectedListener(this);
        reemplazarFragmento(new FragmentoRegistrarProducto());
        reemplazarFragmento2(new FragmentoRegistrarUsuario());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fab.setVisibility(View.VISIBLE);
        switch (id) {
            case R.id.nav_registrar_producto:
                genericoFragmento = new FragmentoRegistrarProducto();
                reemplazarFragmento(genericoFragmento);
                break;
            case R.id.nav_registrar_usuario:
                fab.setVisibility(View.INVISIBLE);
                reemplazarFragmento(new FragmentoRegistrarUsuario());
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void reemplazarFragmento(Fragment fragmento) {
        getSupportFragmentManager().
                beginTransaction().replace(R.id.frmContenido, fragmento).commit();
    }

    private void reemplazarFragmento2(Fragment fragmento) {
        getSupportFragmentManager().
                beginTransaction().replace(R.id.frmContenido2, fragmento).commit();
    }


}
