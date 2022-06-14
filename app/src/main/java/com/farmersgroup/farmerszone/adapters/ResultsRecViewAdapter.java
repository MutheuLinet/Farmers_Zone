package com.farmersgroup.farmerszone.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.models.ResultById;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.farmersgroup.farmerszone.ui.BrowseByIdActivity;

public class ResultsRecViewAdapter extends RecyclerView.Adapter<ResultsRecViewAdapter.Viewholder> {

    private List<ResultById>resultList;
    private Context mContext;

    public ResultsRecViewAdapter(List<ResultById> resultList, Context mContext) {
        this.resultList = resultList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ResultsRecViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.results_list_item, parent, false);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsRecViewAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position) {
//        holder.mTextTfvName.setText(resultList.get(position).getOthname());
        holder.bindResult(resultList.get(position));
        holder.mParentCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, BrowseByIdActivity.class);
                intent.putExtra("tfvitem", resultList.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void setResultList(List<ResultById> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }
    public void setFilteredResultList(List<ResultById> resultListFiltered) {
        this.resultList = resultListFiltered;
        notifyDataSetChanged();
    }
    public class Viewholder extends RecyclerView.ViewHolder{
        @BindView(R.id.textTfvName) TextView mTextTfvName;
        @BindView(R.id.textBotName) TextView mTextBotName;
        @BindView(R.id.textOthName) TextView mTextOthName;
        @BindView(R.id.imageCard)   ImageView mImageCard;
        @BindView(R.id.parentCard)  CardView mParentCard;

        private Context mContext;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
//            itemView.setOnClickListener(this);
        }

        public void bindResult(ResultById result){
            mTextTfvName.setText(result.getTfvname());
            mTextBotName.setText(result.getBotname());
            mTextOthName.setText(result.getOthname());
            Glide.with(mContext)
                    .asBitmap()
                    .load(result.getImageurl())
                    .into(mImageCard);
        }
    }
}
