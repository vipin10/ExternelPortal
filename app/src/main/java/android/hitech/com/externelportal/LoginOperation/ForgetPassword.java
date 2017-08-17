package android.hitech.com.externelportal.LoginOperation;

import android.content.Context;
import android.content.Intent;
import android.hitech.com.externelportal.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class ForgetPassword extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    RadioGroup radioGroup;
    LinearLayout ll;
    EditText et;
    TextInputLayout inputLayout;
    MyAlertDialog forgetDialog;
    LayoutInflater inflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    public void onClickF(View view) {
        if (view.getId()==R.id.fSend){
            forgetDialog = new MyAlertDialog(ForgetPassword.this);
            View view1 = inflater.inflate(R.layout.forget_dialog,null);
            forgetDialog.setView(view1);
            forgetDialog.show();
        }else {
            startActivity(new Intent(ForgetPassword.this,MainActivity.class));
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        View view = inflater.inflate(R.layout.forget_text, null);
        ll = (LinearLayout) findViewById(R.id.ll);
        if (ll.getChildCount() < 5) {
            ll.addView(view, 3);
        }
        inputLayout = (TextInputLayout) findViewById(R.id.dSend);
        et = (EditText) ll.findViewById(R.id.et);
        if (checkedId == R.id.radio1) {
            inputLayout.setHint("Email");
            et.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        } else {
            inputLayout.setHint("Phone");
            et.setInputType(InputType.TYPE_CLASS_PHONE);
        }
    }

    private class MyAlertDialog extends AlertDialog {

        protected MyAlertDialog(@NonNull Context context) {
            super(context);
        }
    }
}
