package com.barranquero.manageproductsrecycler.interfaces;

import android.util.Patterns;

/**
 * Created by usuario on 11/11/16.
 */
public interface IValidateUser extends IValidateAccount {
    int EMAIL_INVALID = 14;

    interface Presenter {
        static int validateCredentialsEmail(String email) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                return EMAIL_INVALID;
            else
                return OK;
        }
    }
}
