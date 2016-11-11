package com.barranquero.manageproductsrecycler;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.barranquero.manageproductsrecycler.interfaces.IValidateAccount;
import com.barranquero.manageproductsrecycler.presenter.Login_Presenter;

/**
 * This application uses the Model-View-Presenter philosophy
 * Class which shows a Login Activity in a RelativeLayout
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class Login_Activity extends AppCompatActivity implements IValidateAccount.View {

    private IValidateAccount.Presenter mLoginMvp;
    private EditText mEdtPassword;
    private EditText mEdtUser;
    private Button mBtnLogin;
    private TextView mTxvForgot;
    private TextView mTxvSignUp;
    private TextInputLayout mTilUser;
    private TextInputLayout mTilPassword;
    private final String TAG = "Login_Activity";

    /**
     * Method which initialises and shows the Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginMvp = new Login_Presenter(this);  // The Presenter has an Activity instance

        mEdtUser = (EditText)findViewById(R.id.edtUser);
        mEdtUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTilUser.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEdtPassword = (EditText)findViewById(R.id.edtPassword);

        mTxvForgot = (TextView)findViewById(R.id.txvForgot);
        mTxvForgot.setMovementMethod(LinkMovementMethod.getInstance());
        Typeface font = Typeface.createFromAsset(getAssets(), "dattermatter.ttf");
        mTxvForgot.setTypeface(font);

        mTxvSignUp = (TextView)findViewById(R.id.txvSign);
        mTxvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, Sign_Activity.class);
                startActivity(intent);
            }
        });

        mBtnLogin = (Button)findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(android.view.View view) {
                String user = mEdtUser.getText().toString();
                String password = mEdtPassword.getText().toString();
                mLoginMvp.validateCredentialsLogin(user, password);
            }
        });

        mTilPassword = (TextInputLayout)findViewById(R.id.tilPassword);
        mTilUser = (TextInputLayout)findViewById(R.id.tilUser);

    }

    /**
     * Method which shows an error to the user
     * @param error The string which explains the occurred error
     */
    @Override
    public void setMessageError(String error, int idView) {
        switch (idView) {
            case R.id.edtPassword:
                mTilPassword.setError(error);
                break;
            case R.id.edtUser:
                mTilUser.setError(error);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroyed Activity");
    }
}