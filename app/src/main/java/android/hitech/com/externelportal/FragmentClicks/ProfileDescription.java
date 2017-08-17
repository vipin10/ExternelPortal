package android.hitech.com.externelportal.FragmentClicks;

import android.hitech.com.externelportal.R;
import android.hitech.com.externelportal.SplashScreen.MyNetwork;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileDescription extends AppCompatActivity {
    ActionBar actionBar;
    String name, org, des, yexp, mexp;
    TextView pName, oName, dName, yExp, mExp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_description);
        networkOperation();
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void networkOperation() {
        String address = "http://172.16.0.6:81/api/MobileApp/GetCandidateProfile?candidateId=1";
        StringRequest request = new StringRequest(Request.Method.GET, address, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    name = jsonObject.getString("CandidateName");
                    yexp = jsonObject.getString("YearExperience");
                    mexp = jsonObject.getString("MonthExperience");
                    org = jsonObject.getString("CompanyName");
                    des = jsonObject.getString("Designation");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pName = (TextView) findViewById(R.id.pName);
                oName = (TextView) findViewById(R.id.oName);
                dName = (TextView) findViewById(R.id.dName);
                yExp = (TextView) findViewById(R.id.yExp);
                mExp = (TextView) findViewById(R.id.mExp);
                pName.setText(name);
                oName.setText(org);
                dName.setText(des);
                yExp.setText(yexp);
                if (mexp.contentEquals("")) {
                    mExp.setText("0");
                }else{
                    mExp.setText(mexp);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MyNetwork.getInstance(ProfileDescription.this).addToRequestQueue(request);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
}
