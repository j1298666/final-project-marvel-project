package com.example.btanner.marveldatabase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by btanner on 6/3/2017.
 */

public class MarvelRVAdapter extends RecyclerView.Adapter<MarvelRVAdapter.MarvelItemViewHolder> {

    private OnMarvelItemClickListener mMarvelItemClickListener;
    private ArrayList<utils.MarvelItem> mMarvelItems;
    public static final String ITEM_ID = "ID";
    public static final String ITEM_CATEGORY = "CATEGORY";

    private String mCategory;
    private Context mContext;

    public MarvelRVAdapter (OnMarvelItemClickListener clickListener) {
        mMarvelItemClickListener = clickListener;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void updateMarvelItems(ArrayList<utils.MarvelItem> marvelItems, String category) {
        mMarvelItems = marvelItems;
        mCategory = category;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mMarvelItems != null) {
            return mMarvelItems.size();
        }
        else {
            return 0;
        }
    }


    @Override
    public MarvelItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.marvel_item, parent, false);
        return new MarvelItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MarvelItemViewHolder holder, int position) {
        holder.bind(mMarvelItems.get(position));
    }

    public interface OnMarvelItemClickListener {
        void onMarvellItemClick(utils.MarvelItem marvelItem);
    }

    class MarvelItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mMarvelItemTV;
        public MarvelItemViewHolder(View itemView) {
            super(itemView);
            mMarvelItemTV = (TextView)itemView.findViewById(R.id.tv_marvel_rc_item);
            itemView.setOnClickListener(this);
        }

        public void bind (utils.MarvelItem marvelItem) {
            if(mCategory.equals("characters")) {
                mMarvelItemTV.setText(marvelItem.name);
            }
            else if(mCategory.equals("comics")) {
                mMarvelItemTV.setText(marvelItem.title);
            }
            else if(mCategory.equals("creators")) {
                mMarvelItemTV.setText(marvelItem.fullName);
            }
            else if(mCategory.equals("events")) {
                mMarvelItemTV.setText(marvelItem.title);
            }
            else if(mCategory.equals("series")) {
                mMarvelItemTV.setText(marvelItem.title);
            }
            else if(mCategory.equals("stories")){
                mMarvelItemTV.setText(marvelItem.title);
            }
            else {
                mMarvelItemTV.setText("Error");
            }
        }

        public void onClick(View v) {
            utils.MarvelItem marvelItem = mMarvelItems.get(getAdapterPosition());
            mMarvelItemClickListener.onMarvellItemClick(marvelItem);
            Log.d("Adapter", "on clikc in adapter ");
//            Intent intent = new Intent(mContext, MarvelItemDetails.class);
//
//            intent.putExtra(ITEM_ID, String.valueOf(marvelItem.id));
//            intent.putExtra(ITEM_CATEGORY, mCategory);
//            mContext.startActivity(intent);
        }
    }
}