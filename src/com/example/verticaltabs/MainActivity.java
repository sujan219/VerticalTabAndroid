package com.example.verticaltabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.util.verticaltab.TabSelectedListener;
import com.util.verticaltab.VerticalTabContentAdapter;
import com.util.verticaltab.VerticalTabLayout;

public class MainActivity extends Activity {
	private VerticalTabLayout verticalTabLayout;
	private String[] tabs = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		verticalTabLayout = (VerticalTabLayout)findViewById(R.id.verticalTabLayout);
		
		verticalTabLayout.setTabSelectedListener(new TabSelectedListener(){
			public void tabSelected(int index){
				Toast.makeText(getApplicationContext(), "Selected tab: " + index, Toast.LENGTH_LONG).show();
			}
		});
		
		verticalTabLayout.setAdapter(new VerticalTabAdapter());
		
		verticalTabLayout.setTabs(this, tabs);
	}
	
	private class VerticalTabAdapter extends VerticalTabContentAdapter{
		@Override
		public View getView(View view, int index) {
			if(view == null){
				LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.tab_content_layout, null);
			}
			
			TextView textView = (TextView)view.findViewById(R.id.text);
			textView.setText("Current selected Tab: " + (index+1));
			
			return view;
		}
	}
}
