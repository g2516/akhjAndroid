package fi.CodeRevolution.kiesi.Utils;

import java.util.HashMap;
import java.util.List;

import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
 
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
 
public class CarAdapter extends BaseExpandableListAdapter {
 
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, Car> _listDataChild;
 
    public CarAdapter(Context context, List<String> listDataHeader,
            HashMap<String, Car> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final Car c = (Car) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_template, null);
        }
 
        Button costs = (Button) convertView.findViewById(R.id.btnKulut);
        Button edit = (Button) convertView.findViewById(R.id.btnEdit);
        Button delete = (Button) convertView.findViewById(R.id.btnDelete);
        costs.setTag(groupPosition);
        edit.setTag(groupPosition);
        delete.setTag(groupPosition);
        
        TextView column1 = (TextView) convertView.findViewById(R.id.column1);
        TextView column2 = (TextView) convertView.findViewById(R.id.column2);
        TextView column3 = (TextView) convertView.findViewById(R.id.column3);
        TextView column4 = (TextView) convertView.findViewById(R.id.column4);
        TextView column5 = (TextView) convertView.findViewById(R.id.column5);
        TextView column6 = (TextView) convertView.findViewById(R.id.column6);
        TextView column7 = (TextView) convertView.findViewById(R.id.column7);
        TextView column8 = (TextView) convertView.findViewById(R.id.column8);
        TextView column9 = (TextView) convertView.findViewById(R.id.column9);
        column1.setText("Nimi :"+c.getName());
        column2.setText("Malli : "+c.getModel());
        column3.setText("Valmistaja : "+c.getManufacturer());
        column4.setText("Kulutus : "+c.getConsumption());
        column5.setText("Kilometrit : "+c.getKilometers());
        column6.setText("Osto pvm :"+c.getDate());
        column7.setText("Hinta :"+c.getPrice());
        column8.setText("Vuosimalli : "+c.getYear());
        column9.setText("Moottori : "+c.getMotor());
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
