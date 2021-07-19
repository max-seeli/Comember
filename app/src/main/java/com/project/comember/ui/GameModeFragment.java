package com.project.comember.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.project.comember.R;
import com.project.comember.game.GameMode;
import com.project.comember.game.GameModeInfo;
import com.project.comember.game.GameStatistics;
import com.project.comember.misc.GameModeAdapter;

import java.util.ArrayList;
import java.util.List;

public class GameModeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_mode_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GameModeAdapter adapter = new GameModeAdapter(getGameModeList(), getContext());

        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);


    }

    private List<GameModeInfo> getGameModeList() {
        Context context = getContext();

        List<GameModeInfo> gameModeInfoList = new ArrayList<>();
        gameModeInfoList.add(
                new GameModeInfo(
                        R.drawable.ic_game_button,
                        getString(R.string.game_mode_classic),
                        getString(R.string.game_mode_classic_description),
                        GameStatistics.getHighScore(context, GameMode.CLASSIC),
                        GameStatistics.getAverageScore(context, GameMode.CLASSIC),
                        GameStatistics.getLastScore(context, GameMode.CLASSIC),
                        R.id.gameModeFragment_to_gameFragment
                ));

        gameModeInfoList.add(
                new GameModeInfo(R.drawable.ic_game_button_spinning,
                        getString(R.string.game_mode_spinning),
                        getString(R.string.game_mode_spinning_description),
                        GameStatistics.getHighScore(context, GameMode.SPINNING),
                        GameStatistics.getAverageScore(context, GameMode.SPINNING),
                        GameStatistics.getLastScore(context, GameMode.SPINNING),
                        R.id.gameModeFragment_to_spinningGameFragment
                ));

        gameModeInfoList.add(
                new GameModeInfo(
                        R.drawable.ic_game_button_no_repeat,
                        getString(R.string.game_mode_no_repeat),
                        getString(R.string.game_mode_no_repeat_description),
                        GameStatistics.getHighScore(context, GameMode.NOREPEAT),
                        GameStatistics.getAverageScore(context, GameMode.NOREPEAT),
                        GameStatistics.getLastScore(context, GameMode.NOREPEAT),
                        R.id.gameModeFragment_to_noRepeatGameFragment
                ));

        return gameModeInfoList;
    }
}