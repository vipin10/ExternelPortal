package android.hitech.com.externelportal.NavigationFragments;

import android.hitech.com.externelportal.R;
import android.hitech.com.externelportal.SplashScreen.MyNetwork;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrentPositions extends Fragment implements RecyclerViewOnItemClick {
    View view;
    RecyclerView rv;
    RecyclerView.LayoutManager lm;
    List<String> stringList;
    List<List<String>> fList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.current_pos, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String address = "http://172.16.0.6:81/api/MobileApp/GetPostedAttachedJob?candidateId=1";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, address, new JSONArray(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        stringList = new ArrayList<>();
                        stringList.add(object.getString("JobTitle"));
                        stringList.add(object.getString("CompanyName"));
                        stringList.add(object.getString("PostedBy"));
                        stringList.add(object.getString("Experience"));
                        stringList.add(object.getString("Location"));
                        stringList.add(object.getString("KeySkills"));
                        stringList.add(object.getString("ProgressStatus"));
                        fList.add(stringList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyNetwork.getInstance(getActivity()).addToRequestQueue(arrayRequest);
        rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        lm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(lm);
        rv.setAdapter(new CurrentPosAdapter(this,fList));
    }

    @Override
    public void onItemClickListener(View view, int position) {
        Toast.makeText(getContext(), "RecyclerItem", Toast.LENGTH_LONG).show();
    }
}
