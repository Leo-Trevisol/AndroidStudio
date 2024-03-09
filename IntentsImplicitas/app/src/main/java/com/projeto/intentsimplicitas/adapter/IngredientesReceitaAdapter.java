package com.projeto.intentsimplicitas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.projeto.intentsimplicitas.R;
import com.projeto.intentsimplicitas.classes.Global;

import java.util.HashMap;
import java.util.List;

public class IngredientesReceitaAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listGroup;
    private List<String>  listChild;

    public IngredientesReceitaAdapter(Context context, List<String> listGroup, List<String>  listChild) {
        this.context = context;
        this.listGroup = listGroup;
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChild.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_ingrediente_group, null);
        }

        TextView textIngredienteTitle = convertView.findViewById(R.id.text_ingrediente_title);
        convertView.findViewById(R.id.imageView_setinha).setRotation(isExpanded ? -90 : 90);
        textIngredienteTitle.setText((String) getGroup(groupPosition));

        FrameLayout container = convertView.findViewById(R.id.everything);
        container.setBackgroundColor(Global.getInstance().getDefaultColorRed());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_ingrediente_child, null);
        }

        TextView textIngredienteChield = convertView.findViewById(R.id.textViewChild);
        textIngredienteChield.setText((String) getChild(groupPosition, childPosition));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
