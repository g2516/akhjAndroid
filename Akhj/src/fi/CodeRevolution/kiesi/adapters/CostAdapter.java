package fi.CodeRevolution.kiesi.adapters;

import java.util.ArrayList;

import fi.CodeRevolution.akhj.R;
import fi.CodeRevolution.kiesi.Models.Car;
import fi.CodeRevolution.kiesi.Models.InspectionCost;
import fi.CodeRevolution.kiesi.Models.InsuranceCost;
import fi.CodeRevolution.kiesi.Models.MaintenanceCost;
import fi.CodeRevolution.kiesi.Models.MyProperties;
import fi.CodeRevolution.kiesi.Models.RefillCost;
import fi.CodeRevolution.kiesi.Models.RepairCost;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CostAdapter extends BaseAdapter {

    Context context;
    String type;
	ArrayList<InsuranceCost> insuranceCosts;
	ArrayList<MaintenanceCost> maintenanceCosts;
	ArrayList<RefillCost> refillCosts;
	ArrayList<RepairCost> repairCosts;
	ArrayList<InspectionCost> inspectionCosts;
	
    private static LayoutInflater inflater = null;

    public CostAdapter(Context context,int carID,String type) {
        // TODO Auto-generated constructor stub
        this.context = context;
        insuranceCosts=MyProperties.getInstance().user.getCars().get(carID).getInsuranceCosts();	
        maintenanceCosts=MyProperties.getInstance().user.getCars().get(carID).getMaintenanceCosts();	
        refillCosts=MyProperties.getInstance().user.getCars().get(carID).getRefillCosts();	
        repairCosts=MyProperties.getInstance().user.getCars().get(carID).getRepairCosts();	
        inspectionCosts=MyProperties.getInstance().user.getCars().get(carID).getInspectionCosts();	
        this.type=type;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	
	@Override
	public int getCount() {
		
		
		if(type.equals("refill"))
		{
			return refillCosts.size();
		}
		else if(type.equals("maintenance"))
		{
			return maintenanceCosts.size();
		}
		else if(type.equals("repair"))
		{
			return repairCosts.size();
		}
		else if(type.equals("insurance"))
		{
			return insuranceCosts.size();
		}
		else if(type.equals("inspection"))
		{
			return inspectionCosts.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if(type.equals("refill"))
		{
			return refillCosts.get(position);
		}
		else if(type.equals("maintenance"))
		{
			return maintenanceCosts.get(position);
		}
		else if(type.equals("repair"))
		{
			return repairCosts.get(position);
		}
		else if(type.equals("insurance"))
		{
			return insuranceCosts.get(position);
		}
		else if(type.equals("inspection"))
		{
			return inspectionCosts.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		if(type.equals("refill"))
		{
			return refillCosts.get(position).getId();
		}
		else if(type.equals("maintenance"))
		{
			return maintenanceCosts.get(position).getId();
		}
		else if(type.equals("repair"))
		{
			return repairCosts.get(position).getId();
		}
		else if(type.equals("insurance"))
		{
			return insuranceCosts.get(position).getId();
		}
		else if(type.equals("inspection"))
		{
			return inspectionCosts.get(position).getId();
		}
		return 0;
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

        column1.setText("Hinta : ");
        column3.setText("Pvm : ");
        
        if(type.equals("refill"))
		{
        	column2.setText(""+refillCosts.get(position).getPrice());
        	column4.setText(""+refillCosts.get(position).getDate());
		}
		else if(type.equals("maintenance"))
		{
			column2.setText(""+maintenanceCosts.get(position).getPrice());
        	column4.setText(""+maintenanceCosts.get(position).getDate());
		
		}
		else if(type.equals("repair"))
		{
			column2.setText(""+repairCosts.get(position).getPrice());
        	column4.setText(""+repairCosts.get(position).getDate());
			
		}
		else if(type.equals("insurance"))
		{
			column2.setText(""+insuranceCosts.get(position).getPrice());
        	column4.setText(""+insuranceCosts.get(position).getDate());
			
		}
		else if(type.equals("inspection"))
		{
			column2.setText(""+inspectionCosts.get(position).getPrice());
        	column4.setText(""+inspectionCosts.get(position).getDate());
			
		}
        return vi;
	}
	
	

}
