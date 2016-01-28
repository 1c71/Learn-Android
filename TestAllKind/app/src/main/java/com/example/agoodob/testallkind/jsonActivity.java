package com.example.agoodob.testallkind;

    import android.app.Activity;
    import android.os.StrictMode;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;

    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.widget.Button;
    import android.widget.TextView;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;

    public class jsonActivity extends Activity {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_json);

            TextView output = (TextView) findViewById(R.id.textView1);
            /*
            String strJson = "{" +
                    "}";
            String data = "";
            try {
                JSONObject  jsonRootObject = new JSONObject(strJson);

                //Get the instance of JSONArray that contains JSONObjects
                JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");

                //Iterate the jsonArray and print the info of JSONObjects
                for(int i=0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    int id = Integer.parseInt(jsonObject.optString("id").toString());
                    String name = jsonObject.optString("name").toString();
                    float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                    data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
                }
                output.setText(data);
            } catch (JSONException e) {e.printStackTrace();}
            */
        }
    }