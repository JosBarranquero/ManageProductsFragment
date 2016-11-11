package com.barranquero.manageproductsrecycler.interfaces;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.barranquero.manageproductsrecycler.Product_Activity;
import com.barranquero.manageproductsrecycler.R;

/**
 * Interface with methods to implement by the View and the Presenter
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public interface IValidateAccount {
    /**
     * Error codes
     */
    int OK = 0;
    int PASSWORD_DIGIT = 10;
    int PASSWORD_CASE = 11;
    int PASSWORD_LENGTH = 12;
    int DATA_EMPTY = 13;

    /**
     * Inner interface to be implemented by the View
     */
    interface View {
        void setMessageError(String error, int idView);
    }

    /**
     * Inner interface to be implemented by the Presenter
     */
    interface Presenter {
        //boolean validateCredentialsUser(String user);
        //boolean validateCredentialsPassword(String password);

        static int validateCredentialsUser(String user) {
            if (TextUtils.isEmpty(user)) {
                return DATA_EMPTY;
            }
            return OK;
        }

        static int validateCredentialsPassword(String password) {
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
}