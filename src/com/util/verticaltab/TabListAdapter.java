package com.util.verticaltab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.verticaltabs.R;

public class TabListAdapter extends BaseAdapter{
	
	private VerticalTabLayout verticalTabLayout;
	private String[] tabs;
	
	public TabListAdapter(VerticalTabLayout verticalTabLayout, String[] tabs){
		this.verticalTabLayout = verticalTabLayout;
		this.tabs = tabs;
	}
	
	@Override
	public int getCount() {
		return tabs.length;
	}

	@Override
	public String getItem(int arg0) {
		return tabs[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int index, View view, ViewGroup arg2) {
		if(view == null){
			LayoutInflater inflater = (LayoutInflater)verticalTabLayout.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.vertical_layout_tab_layout, null);
		}
		TextView textView = (TextView)view.findViewById(R.id.verticalTabText);
		textView.setText(getItem(index));
		
		if(index == verticalTabLayout.getLastSelectedTabIndex()){
			view.setBackgroundResource(R.drawable.vertical_tab_selected_background);
		}else{
			view.setBackgroundResource(R.drawable.vertical_tab_normal_background);
		}
		
		return view;
	}
}