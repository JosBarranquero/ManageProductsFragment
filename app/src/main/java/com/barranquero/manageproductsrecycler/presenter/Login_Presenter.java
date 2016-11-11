package com.barranquero.manageproductsrecycler.presenter;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.barranquero.manageproductsrecycler.Product_Activity;
import com.barranquero.manageproductsrecycler.R;
import com.barranquero.manageproductsrecycler.interfaces.IValidateAccount;



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

    public Login_Presenter(IValidateAccount.View view) {
        this.view = view;
    }

    /**
     * Method which checks whether the password the user has entered complies with the rules and saves the username and password
     *
     * @param user The username entered in the username field
     *             //@param password The password entered in the password field
     */
    public void validateCredentialsLogin(String user, String password) {
        validateUser = IValidateAccount.Presenter.validateCredentialsUser(user);
        validatePassword = IValidateAccount.Presenter.validateCredentialsPassword(password);

        if ((validateUser == IValidateAccount.OK) && validatePassword == IValidateAccount.OK) {
            Intent intent = new Intent((Context)view, Product_Activity.class);
            ((Context)view).startActivity(intent);
        } else {
            switch (validateUser) {

            }
            switch (validatePassword) {
                
            }
        }
    }
}