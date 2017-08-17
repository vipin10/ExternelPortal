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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class JobDescription extends AppCompatActivity implements View.OnClickListener {
    ActionBar actionBar;
    TextView textTitle, textExpe, textLocation, textSkill, textSalary, createdDate, jobDescription, textMore, industry, functionalArea, companyProfile;
    ProgressBar textProgress;
    boolean bool = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_description);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textExpe = (TextView) findViewById(R.id.textExpe);
        textLocation = (TextView) findViewById(R.id.textLocation);
        textSkill = (TextView) findViewById(R.id.textSkill);
        textSalary = (TextView) findViewById(R.id.textSalary);
        createdDate = (TextView) findViewById(R.id.createdDate);
        jobDescription = (TextView) findViewById(R.id.jobDescription);
        textMore = (TextView) findViewById(R.id.textMore);
        textMore.setTextColor(Color.parseColor("#1a5276"));
        textMore.setOnClickListener(this);
        industry = (TextView) findViewById(R.id.industry);
        functionalArea = (TextView) findViewById(R.id.functionalArea);
        companyProfile = (TextView) findViewById(R.id.companyProfile);
        textProgress = (ProgressBar) findViewById(R.id.textProgress);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        String address = "http://172.16.0.6:81/api/MobileApp/GetAttachedJobDescriptionDetails?candidateId=1&JobId=f6a6141f-84ba-415b-b35d-42c8171465eb";
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, address, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    textTitle.setText(response.getString("JobTitle"));
                    textExpe.setText(response.getString("Experience"));
                    textLocation.setText(response.getString("Location"));
                    textSkill.setText(response.getString("KeySkills"));
                    textSalary.setText(response.getString("Salary"));
                    jobDescription.setText(response.getString("JobDescription"));
                    industry.setText(response.getString("Industry"));
                    functionalArea.setText(response.getString("FunctionalArea"));
                    companyProfile.setText(response.getString("CompanyProfile"));
                    textProgress.setProgress(Integer.parseInt(response.getString("ProgressStatus"))*20);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyNetwork.getInstance(this).addToRequestQueue(objectRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

    @Override
    public void onClick(View v) {
        if(!bool){
            bool = true;
            textMore.setText("LESS..");
            jobDescription.setMaxLines(100);
        }else{
            bool = false;
            textMore.setText("MORE..");
            jobDescription.setMaxLines(3);
        }
    }
}
