package com.barranquero.manageproductsrecycler.presenter;


import android.content.Context;
import android.text.TextUtils;

import com.barranquero.manageproductsrecycler.R;
import com.barranquero.manageproductsrecycler.interfaces.LoginPresenter;
import com.barranquero.manageproductsrecycler.utils.ErrorMapUtils;

import static com.barranquero.manageproductsrecycler.model.Error.DATA_EMPTY;
import static com.barranquero.manageproductsrecycler.model.Error.OK;
import static com.barranquero.manageproductsrecycler.model.Error.PASSWORD_CASE;
import static com.barranquero.manageproductsrecycler.model.Error.PASSWORD_DIGIT;
import static com.barranquero.manageproductsrecycler.model.Error.PASSWORD_LENGTH;


/**
 * Class that controls the view and implements the Login rules
 * - At least one upper case and one lower case character
 * - At least one digit
 * - At least 8 characters long
 *
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class LoginPresenterImpl implements LoginPresenter.Presenter {
    private LoginPresenter.View view;
    private int validateUser, validatePassword;
    private Context context;

    public LoginPresenterImpl(LoginPresenter.View view) {
        this.view = view;
        this.context = (Context)view;
    }

    /**
     * Method which checks whether the password the user has entered complies with the rules and saves the username and password
     * @param user The username entered in the username field
     * @param password The password entered in the password field
     */
    public void validateCredentials(String user, String password) {
        validateUser = validateCredentialsUser(user);
        validatePassword = validateCredentialsPassword(password);

        if (validateUser == OK) {
            if (validatePassword == OK) {
                view.startActivity();
            } else {
                String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validatePassword));
                view.setMessageError(nameResource, R.id.tilPassword);
            }
        } else {
            String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.tilUser);
        }
    }

    @Override
    public int validateCredentialsUser(String user) {
        if (TextUtils.isEmpty(user)) {
            return DATA_EMPTY;
        }
        return OK;
    }

    @Override
    public int validateCredentialsPassword(String password) {
        int result = OK;
        if (TextUtils.isEmpty(password)) {
            result = DATA_EMPTY;
        } else {
            if (!(password.matches("(.*)\\d(.*)"))) {
                result = PASSWORD_DIGIT;
            }
            if (!(password.matches("(.*)\\p{Lower}(.*)") && password.matches("(.*)\\p{Upper}(.*)"))) {
                result = PASSWORD_CASE;
            }
            if (password.length() < 8) {
                result = PASSWORD_LENGTH;
            }
        }
        return result;
    }
}