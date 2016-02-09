package com.util.verticaltab;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.verticaltabs.R;

public class VerticalTabLayout extends LinearLayout {
	private LayoutInflater inflater;
	private String[] tabs;
	private TabSelectedListener tabSelectedListener;
	private VerticalTabContentAdapter tabContentDisplayAdapter;
	private ListView verticalTabListView;
	private Activity activity;
	private Context context;
	private View tabContentView;
	private int lastSelectedTabIndex = 0;
	private LinearLayout tabContentLayout;
	
	public VerticalTabLayout(Activity activity, String[] tabs) {
		super(activity.getApplicationContext());
		this.activity = activity;
		this.context = activity.getApplicationContext();
		this.tabs = tabs;
		initializeLayout();
	}
	
	public VerticalTabLayout(Context context){
		super(context);
		this.context = context;
	}
	
	public VerticalTabLayout(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
		this.context = context;
	}
	
	public void setTabs(Activity activity, String[] tabs){
		this.activity = activity;
		this.tabs = tabs;
		initializeLayout();
	}
	
	public void initializeLayout(){
		
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.vertical_layout, null);
		
		tabContentLayout = (LinearLayout)view.findViewById(R.id.vertical_tab_content);
		verticalTabListView = (ListView)view.findViewById(R.id.vertical_tab_list_view);
		verticalTabListView.setAdapter(new TabListAdapter(this, tabs));
		verticalTabListView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				tabClicked(arg2, false);
			}
		});
		
		setAvailableWidth();
		addView(view);
		tabClicked(0, true);
	}
	
	public void setTabSelectedListener(TabSelectedListener tabSelectedListener){
		this.tabSelectedListener = tabSelectedListener;
	}
	
	public void setAdapter(VerticalTabContentAdapter adapter){
		tabContentDisplayAdapter = adapter;
	}
	
	public int getLastSelectedTabIndex(){
		return lastSelectedTabIndex;
	}
	
	public Context getApplicationContext(){
		return context;
	}
	
	private void tabClicked(int index, boolean isTriggeredProgrammatically){
		if(tabSelectedListener != null && !isTriggeredProgrammatically){
			tabSelectedListener.tabSelected(index);
		}
		
		if(tabContentDisplayAdapter != null){
			tabContentView = tabContentDisplayAdapter.getView(tabContentView, index);
			tabContentLayout.removeAllViews();
			tabContentLayout.addView(tabContentView);
		}
		
		lastSelectedTabIndex = index;
		((BaseAdapter)verticalTabListView.getAdapter()).notifyDataSetChanged();
	}
	
	private void setAvailableWidth(){
		Display display = activity.getWindowManager().getDefaultDisplay();
		int widthAvailable = display.getWidth() - (int)getResources().getDimension(R.dimen.vertical_tab_width);
		LayoutParams params = (LayoutParams)tabContentLayout.getLayoutParams();
		Toast.makeText(getApplicationContext(), widthAvailable+"", Toast.LENGTH_SHORT).show();
		params.width = widthAvailable;
	}
}
