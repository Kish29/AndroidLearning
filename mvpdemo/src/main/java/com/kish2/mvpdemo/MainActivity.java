package com.kish2.mvpdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kish2.mvpdemo.bean.User;
import com.kish2.mvpdemo.present.UserPresenter;
import com.kish2.mvpdemo.present.impl.UserPresenterImpl;
import com.kish2.mvpdemo.view.BaseView;
import com.kish2.mvpdemo.view.UserView;

import butterknife.BindView;
import butterknife.ButterKnife;

// View层只专注于把数据显示给用户
public class MainActivity extends AppCompatActivity implements UserView {

    /*
     * 所以这两个控件只是为了展示数据，获取数据的工作要从present拿
     */
    @BindView(R.id.saveButton)
    Button saveButton;
    @BindView(R.id.username)
    EditText usernameText;
    @BindView(R.id.password)
    EditText passwordText;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.showToggle)
    Button showButton;

    private Window window;
    private UserPresenter userPresenter;
    private Thread saveThread;
    private Handler mHandler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 必须先进行绑定
        ButterKnife.bind(this);

        userPresenter = new UserPresenterImpl(this);
        window = getWindow();
        progressBar.setVisibility(View.GONE);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProcessing();
                saveThread = new ChildThread();
                saveThread.start();
                // closeProcessing();
            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 1) {
                    usernameText.setText(msg.obj.toString());
                    passwordText.setText(msg.obj.toString());
                    closeProcessing();
                }
            }
        };

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressBar.getVisibility() == View.VISIBLE)
                    progressBar.setVisibility(View.GONE);
                else if (progressBar.getVisibility() == View.GONE)
                    progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    class ChildThread extends Thread {
        @Override
        public void run() {
            User kish2 = userPresenter.findUserByUsername("kish2");
            Message message = new Message();
            message.what = 1;
            message.obj = kish2;
            mHandler.sendMessage(message);
        }
    }

    private void showProcessing() {
        progressBar.setVisibility(View.VISIBLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void closeProcessing() {
        progressBar.setVisibility(View.GONE);
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


    @Override
    public void setUsername(String username) {
        usernameText.setText(username);
    }

    @Override
    public void setPassword(String password) {
        passwordText.setText(password);
    }

    @Override
    public void error(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUsername() {
        return usernameText.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return passwordText.getText().toString().trim();
    }


    @Override
    protected void onDestroy() {
        userPresenter.detachView();
        super.onDestroy();
    }
}
