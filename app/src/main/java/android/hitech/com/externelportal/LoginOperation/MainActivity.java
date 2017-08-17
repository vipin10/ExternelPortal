package android.hitech.com.externelportal.LoginOperation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.hitech.com.externelportal.DashBoard.Home;
import android.hitech.com.externelportal.R;
import android.hitech.com.externelportal.SplashScreen.MyNetwork;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView forgetPass;
    Button signBtn;
    EditText userName, password;
    boolean check;
    ConnectivityManager cm;
    NetworkInfo nInfo;
    private static final int PASS_LENGTH = 8;
    private static final int EMAIL_LENGTH = 8;
    int userLength, passLength;
    String emailText, passText;
    ArrayList<String> list = new ArrayList<>();
    RelativeLayout activity;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (ProgressBar) findViewById(R.id.pb);
        signBtn = (Button) findViewById(R.id.signBtn);
        signBtn.setOnClickListener(this);
        forgetPass = (TextView) findViewById(R.id.forget);
        forgetPass.setOnClickListener(this);
    }

    public boolean signInOperation() {
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        emailText = userName.getText().toString();
        userLength = emailText.length();
        passText = password.getText().toString();
        passLength = passText.length();
        if (userLength >= EMAIL_LENGTH && passLength >= PASS_LENGTH) {
            //pb.setVisibility(View.VISIBLE);
            signInOperatin();
        } else {
            userName.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            password.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            activity = (RelativeLayout) findViewById(R.id.activity_main);
            Snackbar snackbar = Snackbar.make(activity, "Invalid Credentials! Username & Pass would be atleast 8 Char Long?", Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextSize(15);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }
        return check;
    }

    @Override
    public void onClick(View v) {
        cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo = cm.getActiveNetworkInfo();
        if (nInfo!=null&&nInfo.isConnected()){
            switch (v.getId()) {
                case R.id.signBtn:
                    signInOperation();
                    break;
                case R.id.forget:
                    startActivity(new Intent(MainActivity.this, ForgetPassword.class));
            }
        }else {
            Toast.makeText(this, "No Internet Connection Try agin Later", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    public void signInOperatin() {
     /*   String address = "http://172.16.0.6:81/api/Account/Login?Email=mohd.mursaleen@hitechpeopleinc.com&Pass=Pass@123";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.POST, address, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println("TheResponseIs:" + response);
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    list.add(jsonObject.getString("candidateId"));
                    list.add(jsonObject.getString("candidatename"));
                    list.add(jsonObject.getString("email"));
                    list.add(jsonObject.getString("mobile"));
                    list.add(jsonObject.getString("totalexperience"));
                    list.add(jsonObject.getString("JobId"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (list.size()>2){*/
                    Intent intent = new Intent(MainActivity.this,Home.class);
                    intent.putStringArrayListExtra("user",list);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
              /*  }else {
                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
            }
        });
        MyNetwork.getInstance(MainActivity.this).addToRequestQueue(arrayRequest);*/
    }
}
