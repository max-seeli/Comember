package com.project.comember.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import com.project.comember.R;
import com.project.comember.game.GameMode;

import java.util.List;

public class GameModeAdapter extends PagerAdapter {

    private List<GameMode> gameModeList;
    private Context context;

    public GameModeAdapter(List<GameMode> gameModeList, Context context) {
        this.gameModeList = gameModeList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gameModeList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.game_mode_cards, container, false);

        GameMode gameMode = gameModeList.get(position);

        ImageView cardImage = view.findViewById(R.id.card_image);
        TextView cardTitle = view.findViewById(R.id.card_title);
        TextView cardInfo = view.findViewById(R.id.card_info);
        TextView cardHighScore = view.findViewById(R.id.card_high_score_value);
        TextView cardAvgScore = view.findViewById(R.id.card_avg_score_value);
        TextView cardLastScore = view.findViewById(R.id.card_last_score_value);


        cardImage.setImageResource(gameMode.getImageSource());
        cardTitle.setText(gameMode.getName());
        cardInfo.setText(gameMode.getInfo());
        cardHighScore.setText(Integer.toString(gameMode.getHighScore()));
        cardAvgScore.setText(Integer.toString(gameMode.getAvgScore()));
        cardLastScore.setText(Integer.toString(gameMode.getLastScore()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(container).navigate(gameMode.getActionId());
            }
        });

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
