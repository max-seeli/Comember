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
import com.project.comember.game.GameModeInfo;

import java.util.List;

public class GameModeAdapter extends PagerAdapter {

    private List<GameModeInfo> gameModeInfoList;
    private Context context;

    public GameModeAdapter(List<GameModeInfo> gameModeInfoList, Context context) {
        this.gameModeInfoList = gameModeInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return gameModeInfoList.size();
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

        GameModeInfo gameModeInfo = gameModeInfoList.get(position);

        ImageView cardImage = view.findViewById(R.id.card_image);
        TextView cardTitle = view.findViewById(R.id.card_title);
        TextView cardInfo = view.findViewById(R.id.card_info);
        TextView cardHighScore = view.findViewById(R.id.card_high_score_value);
        TextView cardAvgScore = view.findViewById(R.id.card_avg_score_value);
        TextView cardLastScore = view.findViewById(R.id.card_last_score_value);


        cardImage.setImageResource(gameModeInfo.getImageSource());
        cardTitle.setText(gameModeInfo.getName());
        cardInfo.setText(gameModeInfo.getInfo());
        cardHighScore.setText(Integer.toString(gameModeInfo.getHighScore()));
        cardAvgScore.setText(Float.toString(gameModeInfo.getAvgScore()));
        cardLastScore.setText(Integer.toString(gameModeInfo.getLastScore()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(container).navigate(gameModeInfo.getActionId());
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
