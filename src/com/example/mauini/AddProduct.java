package com.example.mauini;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
///

///

public class AddProduct extends ActionBarActivity {

	ImageView productImageView;

	ImageView galleryImageView;

	private static int RESULT_LOAD_IMAGE = 1;
	// /
	int serverResponseCode = 0;
	// /

	private String selectedImagePath;

	// /added by megha

	String upLoadServerUri = null;

	final String uploadFilePath = "/mnt/sdcard/";

	final String uploadFileName = "img.png";

	Bitmap bp;

	ProgressDialog dialog = null;

	// //

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_product);

		final EditText productNameEditText = (EditText) findViewById(R.id.productNameEditText);

		final EditText actualPriceEditText = (EditText) findViewById(R.id.actualPriceEditText);

		final EditText sellingPriceEditText = (EditText) findViewById(R.id.sellingPriceEditText);

		final EditText productDescEditText = (EditText) findViewById(R.id.productDescEditText);

		productImageView = (ImageView) findViewById(R.id.productImageView);

		galleryImageView = (ImageView) findViewById(R.id.galleryImageView);
		
		upLoadServerUri = "http://192.168.85.226:3000/uploadProduct";				//=============

		productImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(

				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

				startActivityForResult(intent, 0);

			}

		});

		galleryImageView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Intent intent = new Intent(Intent.ACTION_VIEW,

				// Uri.parse("content://media/internal/images/media"));

				// intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

				// startActivity(intent);

				Intent i = new Intent(

						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, RESULT_LOAD_IMAGE);

			}

		});
		
		
		// ========================= NEW =============================
		
		

		Button addProductButton = (Button) findViewById(R.id.addProductButton);

		addProductButton.setOnClickListener(new View.OnClickListener() {

			@SuppressLint("NewApi") @Override
			public void onClick(View v) {

				// TODO Auto-generated method stub

				upLoadServerUri = "/uploadProduct";

				String productName = productNameEditText.getText().toString();

				String actualPrice = actualPriceEditText.getText().toString();

				String sellingPrice = sellingPriceEditText.getText().toString();

				String productDesc = productDescEditText.getText().toString();
				
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
				
				HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.168.85.226:3000/uploadProduct");
				
				List<NameValuePair> pair = new ArrayList<NameValuePair>();
				pair.add(new BasicNameValuePair("post_title",productName));
				pair.add(new BasicNameValuePair("post_excerpt",productDesc));
				pair.add(new BasicNameValuePair("sale_price",sellingPrice));
				pair.add(new BasicNameValuePair("regular_price",actualPrice));
				
				try {
					
					httppost.setEntity(new UrlEncodedFormEntity(pair));
					HttpResponse response = httpclient.execute(httppost);
                    Log.i("HTTP Post", "Response from server node = " + response.getStatusLine().getReasonPhrase() + "  Code = " + response.getStatusLine().getStatusCode());               
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
				
				//////Megha!! :D
//				HttpClient client=new DefaultHttpClient();
//				HttpPost post=new HttpPost("http://192.168.85.226:3000/uploadProduct");
//				
//				
//			
//
//				if (bp != null) {
//
//				}
//
//				dialog = ProgressDialog.show(AddProduct.this, "",
//						"Uploading file", true);
//
//				new Thread(new Runnable() {
//
//					public void run() {
//
//						runOnUiThread(new Runnable() {
//
//							public void run() {
//
//								Toast toast = Toast.makeText(
//										getApplicationContext(),
//										"uploading started.....",
//										Toast.LENGTH_SHORT);
//
//							}
//
//						});
//
//						uploadFile("file1");
//
//					}
//
//				}).start();

			}

		});

	}

	// /by megha

	HttpURLConnection conn = null;

	DataOutputStream dos = null;

	String lineEnd = "\r\n";

	String twoHyphens = "--";

	String boundary = "*****";

//	public int uploadFile(String uploadFileName) {
//		upLoadServerUri = "http://192.168.85.226:3000/uploadProduct";
//
//        File sourceFile = new File(uploadFileName); 
//		int bytesRead, bytesAvailable, bufferSize = 1024*1024*1;
//
//		byte[] buffer = null;
//		byte[] b=null;
////		int bytesRead, bytesAvailable, bufferSize;
//        
//
//		int maxBufferSize = 1 * 1024 * 1024;
//
//		String temp = null;
//
//		if (bp != null) {
//
//			ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
//			bp.compress(Bitmap.CompressFormat.PNG, 100, ByteStream);
//			b = ByteStream.toByteArray();
//			temp = Base64.encodeToString(b, Base64.DEFAULT);
//		//	System.out.println("BP not null"+temp);
//		}
//		URL url = null;
//		try {
//			url = new URL(upLoadServerUri);
//		} catch (MalformedURLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		// Open a HTTP connection to the URL
//		try {
//			
//			
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setDoInput(true); // Allow Inputs
//			conn.setDoOutput(true); // Allow Outputs
//			conn.setUseCaches(false); // Don't use a Cached Copy
//			conn.setRequestMethod("POST");
//
//			conn.setRequestProperty("Connection", "Keep-Alive");
//			conn.setRequestProperty("ENCTYPE", "multipart/form-data");
//			conn.setRequestProperty("Content-Type",
//					"multipart/form-data;boundary=" + boundary);
//			conn.setRequestProperty("uploaded_file", uploadFileName);
//		//	conn.setRequestProperty("post_title","Product1");
//			//conn.setRequestProperty("post_excerpt","Excerpt");
//			
//			dos = new DataOutputStream(conn.getOutputStream());
//			dos.writeBytes(twoHyphens + boundary + lineEnd);
//			dos.writeBytes("Content-Disposition: form-data; name='uploaded file';filename="
//					+ uploadFileName + lineEnd);
//			dos.writeBytes("post_title: Product1;post_excerpt:Excerpt");
//
////			dos.writeBytes(lineEnd);
//			byte[] bytesInImg = temp.getBytes();
//			bytesAvailable = b.length;
//			System.out.println("Byte length "+bytesAvailable);
////			bufferSize = Math.min(bytesAvailable, maxBufferSize);
////			dos.writeBytes("Content-Disposition: form-data; name="
////					+ uploadFileName + ";filename=" + uploadFileName + lineEnd);
////			for (int i = 0; i < bytesInImg.length/2; i = i + bufferSize) {
////				dos.write(bytesInImg, i, i + bufferSize - 1);
////			}
//			/*
//			String str="";
//			for(int i=0;i<b.length-bufferSize;i=i+bufferSize){
//				for(int j=0;j<bufferSize;j++){
//					str+=b[i+j];
//				}
//				dos.writeBytes(str);
//				System.out.println("writinG!!!"+str);
//				str="";
//			}
//			for(int j=0;j<b.length-bufferSize;j++){
//				str+=b[j];
//			}
//			dos.writeBytes(str);
//			System.out.println("writinG!!!"+str);
//			str="";
//		*/
//			// read file and write it into form...
//              
//           // while (bytesRead > 0) {
//           	 //
//              //dos.write(buffer, 0, bufferSize);
//              //Log.e("Inside", buffer+"");
//             // bytesAvailable = fileInputStream.available();
//             // bufferSize = Math.min(bytesAvailable, maxBufferSize);
//             // bytesRead = fileInputStream.read(buffer, 0, bufferSize);               
//            // }
//
//
//			dos.writeBytes(lineEnd);
//			dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
//			System.out.println("Sending complete");
//			
//			serverResponseCode = conn.getResponseCode();
//			String serverResponseMessage = conn.getResponseMessage();
//
//			Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage
//					+ ": " + serverResponseCode);
//
//			if (serverResponseCode == 200) {
//
//				runOnUiThread(new Runnable() {
//					public void run() {
//
//						Toast.makeText(getApplicationContext(),
//								"Upload complete", Toast.LENGTH_LONG);
//
//					}
//				});
//			}
//
//			// close the streams //
//			dos.flush();
//			dos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return 0;
//
//	}

	// /

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// TODO Auto-generated method stub

		super.onActivityResult(requestCode, resultCode, data);

		// Bitmap bp = (Bitmap) data.getExtras().get("data");

		// productImageView.setImageBitmap(bp);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {

			Uri selectedImage = data.getData();

			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,

			filePathColumn, null, null, null);

			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

			String picturePath = cursor.getString(columnIndex);

			cursor.close();

			galleryImageView.setImageBitmap(BitmapFactory
					.decodeFile(picturePath));

			// /added by megha

			bp = (Bitmap) BitmapFactory.decodeFile(picturePath);
			System.out.println("bitmap constructed" + bp);

		}

		else

		{

			bp = (Bitmap) data.getExtras().get("data");

			productImageView.setImageBitmap(bp);

		}

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