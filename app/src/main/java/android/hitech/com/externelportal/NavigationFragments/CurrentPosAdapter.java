package android.hitech.com.externelportal.NavigationFragments;

import android.content.Context;
import android.content.Intent;
import android.hitech.com.externelportal.FragmentClicks.JobDescription;
import android.hitech.com.externelportal.R;
import android.hitech.com.externelportal.SplashScreen.MyNetwork;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

class CurrentPosAdapter extends RecyclerView.Adapter<CurrentPosAdapter.ViewHolder> {
    LayoutInflater inflater;
    ViewHolder viewHolder;
    RecyclerViewOnItemClick listener;
    Context context;
    ImageView pChat, exit;
    ImageButton chatBtn;
    MyChatDialog dialog;
    List<List<String>> setList;
    TextView designation, cpny, experience, location, skills;
    ProgressBar progressBar;

    CurrentPosAdapter(RecyclerViewOnItemClick listener, List<List<String>> fList) {
        this.listener = listener;
        this.setList = fList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.currentpos_adapter, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        designation.setText(setList.get(position).get(0));
        cpny.setText(setList.get(position).get(1));
        experience.setText(setList.get(position).get(3));
        location.setText(setList.get(position).get(4));
        skills.setText(setList.get(position).get(5));
        progressBar.setProgress(Integer.parseInt(setList.get(position).get(6)) * 20);
    }

    @Override
    public int getItemCount() {
        return setList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ViewHolder(View itemView) {
            super(itemView);
            designation = (TextView) itemView.findViewById(R.id.designation);
            cpny = (TextView) itemView.findViewById(R.id.cpny);
            experience = (TextView) itemView.findViewById(R.id.experience);
            location = (TextView) itemView.findViewById(R.id.location);
            skills = (TextView) itemView.findViewById(R.id.skills);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            pChat = (ImageView) itemView.findViewById(R.id.pChat);
            pChat.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pChat:
                    chatOperation();
                    break;
                case R.id.cardView:
                    context.startActivity(new Intent(context, JobDescription.class));
            }
        }

        private void chatOperation() {
            View view = inflater.inflate(R.layout.chat_box, null, false);
            dialog = new MyChatDialog(context);
            dialog.setView(view);
            dialog.show();
            chatBtn = (ImageButton) dialog.findViewById(R.id.chatBtn);
            if (chatBtn != null) {
                chatBtn.setOnClickListener(dialogListener);
            }
            exit = (ImageView) dialog.findViewById(R.id.exit);
            if (exit != null) {
                exit.setOnClickListener(dialogListener);
            }
        }

        View.OnClickListener dialogListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.exit:
                        dialog.cancel();
                        break;
                    case R.id.chatBtn:
                        EditText et = (EditText) dialog.findViewById(R.id.et);
                        if (et != null) {
                            String message = et.getText().toString();
                            sendToServer(message);
                        }
                }
            }
        };

        private void sendToServer(String s) {
            s = s.replaceAll("\\s+","+");
            System.out.println("TheMessageIs:" + s);
            String address = "http://172.16.0.6:81/api/MobileApp/AddNewComment?jobId=f6a6141f-84ba-415b-b35d-42c8171465eb&txtMessage="+s+"&createdBy=Jhon&candidateId=1";
            StringRequest request = new StringRequest(Request.Method.POST, address, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error:" + error, Toast.LENGTH_LONG).show();
                }
            });
            MyNetwork.getInstance(context).addToRequestQueue(request);
        }
    }

    class MyChatDialog extends AlertDialog {

        MyChatDialog(@NonNull Context context) {
            super(context);
        }

    }
}
