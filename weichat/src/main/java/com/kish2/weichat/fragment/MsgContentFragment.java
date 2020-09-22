package com.kish2.weichat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kish2.weichat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MsgContentFragment extends Fragment {

    @BindView(R.id.txt_content)
    TextView textView;

    private String page_name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        assert arguments != null;
        page_name = arguments.getString("page_name");
        if (page_name == null)
            page_name = "参数非法";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_content, container, false);
        ButterKnife.bind(this, view);

        textView.setText(page_name);
        return view;
    }
}
