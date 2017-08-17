package android.hitech.com.externelportal.FragmentClicks;

import android.graphics.Color;
import android.hitech.com.externelportal.R;
import android.hitech.com.externelportal.SplashScreen.MyNetwork;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JobDescription1 extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    TextView textT, textEX, textVL, textSkL, textS, textPJ, textJD, textIndustry, textFC, textCP, textMore1;
    boolean bool=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_description1);
        textT = (TextView) findViewById(R.id.textT);
        textEX = (TextView) findViewById(R.id.textEX);
        textVL = (TextView) findViewById(R.id.textVL);
        textSkL = (TextView) findViewById(R.id.textSkL);
        textS = (TextView) findViewById(R.id.textS);
        textPJ = (TextView) findViewById(R.id.textPJ);
        textJD = (TextView) findViewById(R.id.textJD);
        textMore1 = (TextView) findViewById(R.id.textMore1);
        textMore1.setTextColor(Color.parseColor("#1a5276"));
        textMore1.setOnClickListener(this);
        textIndustry = (TextView) findViewById(R.id.textIndustry);
        textFC = (TextView) findViewById(R.id.textFC);
        textCP = (TextView) findViewById(R.id.textCP);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        String address = "http://172.16.0.6:81/api/MobileApp/GetApplyJobDescription?candidateId=1&JobId=39544424-6d55-4c3f-913b-3be0ccfd6f75";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, address, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    textT.setText(jsonObject.getString("JobTitle"));
                    textEX.setText(jsonObject.getString("Experience"));
                    textVL.setText(jsonObject.getString("Location"));
                    textSkL.setText(jsonObject.getString("KeySkills"));
                    textS.setText(jsonObject.getString("Salary"));
                    textJD.setText(jsonObject.getString("JobDescription"));
                    textIndustry.setText(jsonObject.getString("Industry"));
                    textFC.setText(jsonObject.getString("FunctionalArea"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyNetwork.getInstance(this).addToRequestQueue(arrayRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

    public void applyJob(View view) {
        String address = "http://172.16.0.6:81/api/MobileApp/AddAppliedJob?candidateId=1&jobId=f6a6141f-84ba-415b-b35d-42c8171465eb";
        StringRequest request = new StringRequest(Request.Method.GET, address, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(JobDescription1.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyNetwork.getInstance(this).addToRequestQueue(request);
    }

    @Override
    public void onClick(View v) {
        if(!bool){
            bool = true;
            textMore1.setText("LESS..");
            textJD.setMaxLines(100);
        }else{
            bool = false;
            textMore1.setText("MORE..");
            textJD.setMaxLines(3);
        }
    }
}
