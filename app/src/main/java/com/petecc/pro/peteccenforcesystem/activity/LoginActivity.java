package com.petecc.pro.peteccenforcesystem.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.petecc.pro.peteccenforcesystem.R;
import com.petecc.pro.peteccenforcesystem.base.BaseActivity;
import com.petecc.pro.peteccenforcesystem.model.LoginResult;
import com.petecc.pro.peteccenforcesystem.model.User;
import com.petecc.pro.peteccenforcesystem.model.UserInfoResult;
import com.petecc.pro.peteccenforcesystem.presenter.LoginPresenter;
import com.petecc.pro.peteccenforcesystem.utils.Constant;
import com.petecc.pro.peteccenforcesystem.utils.EncoderUtil;
import com.petecc.pro.peteccenforcesystem.utils.IOUtil;
import com.petecc.pro.peteccenforcesystem.utils.StringUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.user_name)
    EditText userNameEdt;
    @BindView(R.id.pass_word)
    EditText passWordEdt;
    @BindView(R.id.login_carview)
    TextView loginBtn;
    @BindView(R.id.remenber_password)
    CheckBox remenberBox;
    private boolean isResetPassWord;//修改密码的标识
    private String passWord;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        // 获取是否选中记住密码选项
        if (IOUtil.readBoolean("remeberUser") && !isResetPassWord) {
            remenberBox.setChecked(true);
        } else {
            remenberBox.setChecked(false);
        }
        // 若记住密码并且不是来自修改密码后跳转，则填充数据，否则，清除用户信息。
        if (remenberBox.isChecked() && !isResetPassWord) {
            // 读取记住的用户名密码，并填充
            String username = EncoderUtil.decode(Constant.SERCRET_KEY, IOUtil.readString("reme_user"));
            String password = EncoderUtil.decode(Constant.SERCRET_KEY, IOUtil.readString("reme_pass"));
            if (!StringUtils.isEmpty(username)) {
                userNameEdt.setText(username);
            }
            if (!StringUtils.isEmpty(password)) {
                passWordEdt.setText(password);
            }
            if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(password)) {
                loginBtn.setClickable(true);
                loginBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        } else {
            userNameEdt.setText("");
            passWordEdt.setText("");
        }
        // 设置监听事件，若取消“记住密码”，则清除存储数据
        remenberBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if (!arg1) {
                    IOUtil.writeBoolean("remeberUser", false);
                    IOUtil.writeString("reme_user", "");
                    IOUtil.writeString("reme_pass", "");
                } else {
                    IOUtil.writeBoolean("remeberUser", true);
                }
            }
        });
        passWordEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().trim().length()>=6) {
                    loginBtn.setClickable(true);
                    loginBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    loginBtn.setClickable(false);
                    loginBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }
        });

    }

    @OnClick({R.id.login_carview})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_carview :
               // if (verify())
                loginIng();
               // toMainActivity();
                break;
        }
    }

    private boolean verify() {
        passWord = passWordEdt.getText().toString().trim();
        userName = userNameEdt.getText().toString().trim();
        if (StringUtils.isEmpty(userName)) {
            toastShow(R.string.username_empty);
            return false;
        }
        if (StringUtils.isEmpty(passWord)) {
            toastShow(R.string.password_empty);
            return false;
        }
        return true;
    }

    private void loginIng() {
        HashMap<String,String> map = new HashMap<>();
        map.put("mobile","15810681269");
        map.put("password", "123456");
//        map.put("imei","861945036726456");
//        map.put("phone","18518928600");
        creatPresenter();
        presenter.login(map);
    }

    @Override
    public void getDataSuccess(Object result) {
        dealResult((LoginResult) result);
    }

    private void dealResult(LoginResult result) {
        if(result.getErrno()==0) {
            toastShow(result.getErrmsg());
            toMainActivity();
        }else{
            toMainActivity();
        }
    }

    private void saveData(User user) {
        IOUtil.writeString(Constant.KEY_USER_ORG_ID, EncoderUtil.encode(Constant.SERCRET_KEY,user.getOrgid()));
        IOUtil.writeString(Constant.KEY_USER_ORG, EncoderUtil.encode(Constant.SERCRET_KEY,user.getOrgname()));
        IOUtil.writeString(Constant.KEY_USER_ID, EncoderUtil.encode(Constant.SERCRET_KEY,user.getUserid()));
        IOUtil.writeString(Constant.KEY_EMPCODE, EncoderUtil.encode(Constant.SERCRET_KEY,user.getEmpcode()));
        IOUtil.writeString(Constant.KEY_USER_NAME, EncoderUtil.encode(Constant.SERCRET_KEY,user.getRealname()));
        IOUtil.writeString(Constant.KEY_ORGCODE, EncoderUtil.encode(Constant.SERCRET_KEY,user.getOrgcode()));
        IOUtil.writeString(Constant.KEY_ORGLEVEL, EncoderUtil.encode(Constant.SERCRET_KEY,user.getOrglevel()+ ""));
    }

    private void toMainActivity() {
        Intent intent = new Intent(mContext,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getDataFail(String msg) {
        toastShow(msg);
        toMainActivity();
    }

    @Override
    public void creatPresenter() {
        if(presenter==null) {
            presenter = new LoginPresenter(this);
        }
    }
}

