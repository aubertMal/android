package com.malekAubert.android.movieapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.malekAubert.android.movieapp.R;
import com.malekAubert.android.movieapp.models.Movie;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
  private Context mContext;
  private ArrayList<Movie> mMovies;

  public SearchAdapter(Context mContext, ArrayList<Movie> mMovies) {
    this.mContext = mContext;
    this.mMovies = mMovies;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView mItemImageView;
    public TextView mTextViewTitle;
    public TextView mTextViewReleased;

    public ViewHolder(View view) {
      super(view);
      mTextViewTitle = view.findViewById(R.id.itemTitle);
      mTextViewReleased = view.findViewById(R.id.itemReleased);
      mItemImageView = view.findViewById(R.id.itemImage);
    }
  }

  @Override
  public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View v = LayoutInflater.from(mContext).inflate(R.layout.item_search_movie, parent, false);
    return new ViewHolder(v);
  }

  @Override
  public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
    Movie mMovie = mMovies.get(position);
    Picasso.get().load(mMovie.getUrlPoster()).resize(50, 50).into(holder.mItemImageView);
    holder.mTextViewTitle.setText(mMovie.getTitle());
    holder.mTextViewReleased.setText(mMovie.getReleaseDate());
  }

  @Override
  public int getItemCount() {
    return mMovies.size();
  }
}
