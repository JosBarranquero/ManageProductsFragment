package com.barranquero.manageproductsrecycler.presenter;


import android.content.Context;

import com.barranquero.manageproductsrecycler.R;
import com.barranquero.manageproductsrecycler.interfaces.IValidateAccount;
import com.barranquero.manageproductsrecycler.model.Error;
import com.barranquero.manageproductsrecycler.utils.ErrorMapUtils;


/**
 * Class that controls the view and implements the Login rules
 * - At least one upper case and one lower case character
 * - At least one digit
 * - At least 8 characters long
 *
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class Login_Presenter implements IValidateAccount.Presenter {
    private IValidateAccount.View view;
    private int validateUser, validatePassword;
    private Context context;

    public Login_Presenter(IValidateAccount.View view) {
        this.view = view;
        this.context = (Context)view;
    }

    /**
     * Method which checks whether the password the user has entered complies with the rules and saves the username and password
     * @param user The username entered in the username field
     * @param password The password entered in the password field
     */
    public boolean validateCredentials(String user, String password) {
        validateUser = IValidateAccount.Presenter.validateCredentialsUser(user);
        validatePassword = IValidateAccount.Presenter.validateCredentialsPassword(password);

        if (validateUser == Error.OK) {
            if (validatePassword == Error.OK) {
                view.startActivity();
            } else {
                String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validatePassword));
                view.setMessageError(nameResource, R.id.tilPassword);
            }
        } else {
            String nameResource = ErrorMapUtils.getErrorMap(context).get(String.valueOf(validateUser));
            view.setMessageError(nameResource, R.id.tilUser);
        }
        return true;
    }
}