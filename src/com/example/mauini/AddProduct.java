package com.example.mauini;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProduct extends ActionBarActivity {

	URL url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_product);
		
		final EditText productNameEditText = (EditText) findViewById(R.id.productNameEditText);
		final EditText actualPriceEditText = (EditText) findViewById(R.id.actualPriceEditText);
		final EditText sellingPriceEditText = (EditText) findViewById(R.id.sellingPriceEditText);
		final EditText productDescEditText = (EditText) findViewById(R.id.productDescEditText);
		
		
		Button addProductButton = (Button) findViewById(R.id.addProductButton);
				
		addProductButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String productName = productNameEditText.getText().toString();
				String actualPrice = actualPriceEditText.getText().toString();
				String sellingPrice = sellingPriceEditText.getText().toString();
				String productDesc = productDescEditText.getText().toString();
				
				
				
				
				try {
					
					url = new URL("https://mauini.staging.wpengine.com/wc-api/v1/add-product/sconsumer_key=ck_58b7fc2888bfb2af86721ba4e305c699&consumer_secret=cs_c5f3ba30001304858ba447c2f1440d65");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					//conn.addRequestProperty("", );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_product, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
