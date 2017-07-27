package co.progredi.vistaspersonalizadas.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.progredi.vistaspersonalizadas.R;
import co.progredi.vistaspersonalizadas.modelo.dto.ItemDTO;

/**
 * Created by lrey on 7/24/17.
 */

public class ItemAdapter extends BaseAdapter implements CheckBox.OnCheckedChangeListener {

    private ArrayList<ItemDTO> listaContactos;
    private Context contexto;

    public ItemAdapter(ArrayList<ItemDTO> listaContactos, Context contexto) {
        this.listaContactos = listaContactos;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return listaContactos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaContactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaContactos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(contexto).inflate(R.layout.item_contacto, null);
        }
        ItemDTO info = listaContactos.get(position);
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvTitulo);
        TextView tvDescripcion = (TextView) convertView.findViewById(R.id.tvDescripcion);
        CheckBox chkSeleccion = (CheckBox) convertView.findViewById(R.id.chkSeleccion);
        chkSeleccion.setOnCheckedChangeListener(this);
        chkSeleccion.setTag(position);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);

        tvTitulo.setText(info.getTitulo());
        tvDescripcion.setText(info.getDescripcion());
        return convertView;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int position = (int) buttonView.getTag();
        ItemDTO info = listaContactos.get(position);
        Toast.makeText(contexto, " Selección " + isChecked + " id: " + info.getId(), Toast.LENGTH_LONG).show();
    }
}
