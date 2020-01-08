package com.shameem.test.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shameem.test.R;
import com.shameem.test.pref.SharedPrefs;
import com.shameem.test.ui.model.Result;
import com.shameem.test.ui.moviedetails.MainDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHold> {

    private List<Result> movieList;
    private Context context;


    public MovieAdapter(Context context, List<Result> movieList) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold holder, int position) {
        Result movie = movieList.get(position);
        holder.tvMovieName.setText(movie.getTitle());
        holder.tvEpisodeNumber.setText(String.valueOf(movie.getEpisodeId()));
        holder.tvDirector.setText(movie.getDirector());
        holder.cardMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(MainDetailsActivity.start(context));
                SharedPrefs.setInt(SharedPrefs.Keys.EPISODE_ID, movie.getEpisodeId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        @BindView(R.id.cardMovie)
        CardView cardMovie;
        @BindView(R.id.tvMovieName)
        TextView tvMovieName;
        @BindView(R.id.tvEpisodeNumber)
        TextView tvEpisodeNumber;
        @BindView(R.id.tvDirector)
        TextView tvDirector;


        public ViewHold(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
