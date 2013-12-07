package fi.CodeRevolution.kiesi.Utils;

import java.util.ArrayList;

import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CarAdapter extends BaseAdapter {

    Context context;
    ArrayList<Car> data;
    private static LayoutInflater inflater = null;

    public CarAdapter(Context context, ArrayList<Car> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return data.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row_template, null);
        TextView column1 = (TextView) vi.findViewById(R.id.column1);
        TextView column2 = (TextView) vi.findViewById(R.id.column2);
        TextView column3 = (TextView) vi.findViewById(R.id.column3);
        TextView column4 = (TextView) vi.findViewById(R.id.column4);

        column1.setText("Valmistaja : ");
        column3.setText("Malli : ");
        column2.setText(data.get(position).getManufacturer());
        column4.setText(data.get(position).getModel());
        return vi;
	}
	
	

}
