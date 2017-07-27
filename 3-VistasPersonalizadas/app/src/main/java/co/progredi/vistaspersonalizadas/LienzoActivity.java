package co.progredi.vistaspersonalizadas;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;

import co.progredi.vistaspersonalizadas.componentes.LienzoView;

/**
 * Created by lrey on 7/24/17.
 */

public class LienzoActivity extends Activity implements View.OnClickListener {


    private LienzoView lienzo;
    private android.widget.Button btnNegro;
    private android.widget.Button btnRojo;
    private android.widget.Button btnAmarillo;
    private android.widget.Button btnBorrador;
    private android.widget.Button btnExportar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lienzo);
        this.btnBorrador = (Button) findViewById(R.id.btnBorrador);
        this.btnAmarillo = (Button) findViewById(R.id.btnAmarillo);
        this.btnRojo = (Button) findViewById(R.id.btnRojo);
        this.btnNegro = (Button) findViewById(R.id.btnNegro);
        this.lienzo = (LienzoView) findViewById(R.id.lienzo);
        this.btnExportar = (Button) findViewById(R.id.btnExportar);


        this.btnAmarillo.setOnClickListener(this);
        this.btnRojo.setOnClickListener(this);
        this.btnNegro.setOnClickListener(this);
        this.btnBorrador.setOnClickListener(this);
        this.btnExportar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNegro:
                lienzo.setColor(Color.BLACK);
                break;
            case R.id.btnAmarillo:
                lienzo.setColor(Color.YELLOW);
                break;
            case R.id.btnRojo:
                lienzo.setColor(Color.RED);
                break;
            case R.id.btnBorrador:
                lienzo.setColor(Color.WHITE);
                break;
            case R.id.btnExportar:
                exportar();
                break;
        }
    }

    private void exportar() {
        View content = findViewById(R.id.lienzo);
        content.setBackgroundColor(Color.WHITE);
        String yourimagename = "MyImageFile";
        File storage = Environment.getExternalStorageDirectory();
        if (!storage.exists()) {
            storage.mkdirs();
        }
        String name = storage + "/" + yourimagename + ".png";
        content.setDrawingCacheEnabled(true);
        Bitmap bitmap = content.getDrawingCache();
        File file = new File(name);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
            ostream.close();
            content.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            content.setDrawingCacheEnabled(false);
        }
    }
}
