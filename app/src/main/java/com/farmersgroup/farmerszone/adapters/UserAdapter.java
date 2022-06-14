package com.farmersgroup.farmerszone.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.models.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class UserAdapter extends FirebaseRecyclerAdapter<User, UserAdapter.AccountHolder>{

    public UserAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }
    @Override
    public void onBindViewHolder(@NonNull AccountHolder holder, int position, @NonNull User model) {
        holder.textViewName.setText(model.getName());
        holder.textViewDescription.setText(model.getEmail());
    }
    @NonNull
    @Override
        public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view,
                parent, false);
        return new AccountHolder(v);
    }
    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getRef().removeValue();
    }
    class AccountHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewDescription;

        private Context mContext;
        public AccountHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.fun);
            textViewDescription = itemView.findViewById(R.id.userphone);
            mContext = itemView.getContext();
        }
    }
}
