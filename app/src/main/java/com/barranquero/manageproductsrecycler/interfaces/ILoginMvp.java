package com.barranquero.manageproductsrecycler.interfaces;

/**
 * Interface with methods to implement by the View and the Presenter
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public interface ILoginMvp {
    /**
     * Error codes
     */
    int PASSWORD_DIGIT = 1;
    int PASSWORD_CASE = 2;
    int PASSWORD_LENGTH = 3;
    int DATA_EMPTY = 4;

    /**
     * Inner interface to be implemented by the View
     */
    interface View {
        void setMessageError(String error, int idView);
        void launchActivity();
    }

    /**
     * Inner interface to be implemented by the Presenter
     */
    interface Presenter {
        void validateCredentials(String user, String password);
    }
}