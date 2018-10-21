package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;

import java.util.List;

public class SpinAdapter extends ArrayAdapter<Categoria> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<Categoria> listaCategorias;

    public SpinAdapter(Context context, int textViewResourceId,
                       List<Categoria> listaCategorias) {
        super(context, textViewResourceId, listaCategorias);
        this.context = context;
        this.listaCategorias = listaCategorias;
    }

    @Override
    public int getCount(){
        return listaCategorias.size();
    }

    @Override
    public Categoria getItem(int position){
        return listaCategorias.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(listaCategorias.get(position).getDescricao());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(listaCategorias.get(position).getDescricao());

        return label;
    }
}