package com.barranquero.manageproductsrecycler.presenter;

import com.barranquero.manageproductsrecycler.interfaces.IValidateUser;

/**
 * Presenter
 */
public class Signup_Presenter implements IValidateUser.Presenter, IValidateUser.PresenterUser {

    public boolean validateCredentials(String user, String password) {
        return true;
    }
}
