package android.hitech.com.externelportal.NavigationFragments;

import android.content.Context;
import android.content.Intent;
import android.hitech.com.externelportal.DatabaseOperation.ApplyJobs;
import android.hitech.com.externelportal.FragmentClicks.JobDescription1;
import android.hitech.com.externelportal.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ViewHolder> {
    RecyclerViewOnItemClick listener;
    LayoutInflater inflater;
    View view;
    Context context;
    TextView applyText,pDesignation,pExperience,pLocation,pSkills;
    ApplyJobs database;
    List<List<String>> setList;

    public ProfilesAdapter(RecyclerViewOnItemClick listener, List<List<String>> fList) {
        this.listener = listener;
        this.setList = fList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        inflater = LayoutInflater.from(context);
        database = new ApplyJobs(context);
        view = inflater.inflate(R.layout.profiles_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        pDesignation.setText(setList.get(position).get(0));
        pExperience.setText(setList.get(position).get(1));
        pLocation.setText(setList.get(position).get(2));
        pSkills.setText(setList.get(position).get(3));
        if (database.getEntry(position)){
            applyText.setText("Applied");
        }
    }

    @Override
    public int getItemCount() {
        return setList.size() ;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View itemView;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            pDesignation = (TextView) itemView.findViewById(R.id.pDesignation);
            pExperience = (TextView) itemView.findViewById(R.id.pExperience);
            pLocation = (TextView) itemView.findViewById(R.id.pLocation);
            pSkills = (TextView) itemView.findViewById(R.id.pSkills);
            applyText = (TextView) itemView.findViewById(R.id.apply);
            applyText.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.apply) {
                database = new ApplyJobs(context);
                database.addEntry(this.getLayoutPosition());
                TextView textView = (TextView) v;
                textView.setText("Applied");
            } else {
                context.startActivity(new Intent(context, JobDescription1.class));
            }
        }
    }
}

