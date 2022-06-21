package com.farmersgroup.farmerszone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farmersgroup.farmerszone.R;
import com.farmersgroup.farmerszone.models.ResultById;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultByIdRecViewAdapter extends RecyclerView.Adapter<ResultByIdRecViewAdapter.Viewholder>{

        private List<ResultById> resultList;
        private Context mContext;

        public ResultByIdRecViewAdapter(List<ResultById> resultList, Context mContext) {
            this.resultList = resultList;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public ResultByIdRecViewAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_by_id, parent, false);
            Viewholder holder = new Viewholder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Viewholder holder, int position) {
            holder.bindResult(resultList.get(position));
        }

        @Override
        public int getItemCount() {
            return resultList.size();
        }

    public static List<ResultById> getResultList(List<ResultById> resultList) {
        return resultList;
    }

    public void setResultList(List<ResultById> resultList) {
            this.resultList = resultList;
            notifyDataSetChanged();
        }

        public class Viewholder extends RecyclerView.ViewHolder{
            @BindView(R.id.textTfvName) TextView mTextTfvName;
            @BindView(R.id.textBotName) TextView mTextBotName;
            @BindView(R.id.textOthName) TextView mTextOthName;
            @BindView(R.id.imageCard)   ImageView mImageCard;
            @BindView(R.id.textClimate) TextView mTextClimate;
            @BindView(R.id.textDescription) TextView mTextDescription;
            @BindView(R.id.textHealth) TextView mTextHealth;
            @BindView(R.id.textPropagation) TextView mTextPropagation;
            @BindView(R.id.textUses) TextView mTextUses;

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
                mTextClimate.setText(result.getClimate());
                mTextDescription.setText(result.getDescription());
                mTextUses.setText(result.getUses());
                mTextHealth.setText(result.getHealth());
                mTextPropagation.setText(result.getPropagation());
                Glide.with(mContext)
                        .asBitmap()
                        .load(result.getImageurl())
                        .into(mImageCard);
            }
        }
    }

