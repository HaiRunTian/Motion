package com.james.motion.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.james.motion.R;

public class ScrollingFragment extends Fragment {
    private static final String ARG_COLUMN_COUNT = "column-count";
    public static ScrollingFragment newInstance(String columnCount) {
        ScrollingFragment fragment = new ScrollingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COLUMN_COUNT, columnCount);

        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_scrolling, container, false);
         TextView textView = inflate.findViewById(R.id.tv);
         textView.setText(getArguments().getString(ARG_COLUMN_COUNT));
        return inflate;
    }
}