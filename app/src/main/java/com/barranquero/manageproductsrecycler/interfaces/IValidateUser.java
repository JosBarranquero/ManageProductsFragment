package com.barranquero.manageproductsrecycler.interfaces;

import android.util.Patterns;

import static com.barranquero.manageproductsrecycler.model.Error.EMAIL_INVALID;
import static com.barranquero.manageproductsrecycler.model.Error.OK;

/**
 * Created by usuario on 11/11/16.
 */
public interface IValidateUser extends IValidateAccount {
    interface PresenterUser {
        static int validateCredentialsEmail(String email) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                return EMAIL_INVALID;
            else
                return OK;
        }
    }
}
