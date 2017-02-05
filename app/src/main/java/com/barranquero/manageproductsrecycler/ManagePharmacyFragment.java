package com.barranquero.manageproductsrecycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.barranquero.manageproductsrecycler.interfaces.IPharmacy;
import com.barranquero.manageproductsrecycler.model.Pharmacy;
import com.barranquero.manageproductsrecycler.model.Product;
import com.barranquero.manageproductsrecycler.presenter.ManagePharmacyPresenterImpl;

/**
 * Fragment which will enable us to edit and add pharmacies
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 * ManageProductsFragment
 */
public class ManagePharmacyFragment extends Fragment implements ManagePharmacyPresenterImpl.View {
    private EditText edtCif, edtAddress, edtPhone, edtEmail;
    private Button btnAdd;
    private ManagePharmacyPresenterImpl managePharmacyPresenter;
    Pharmacy pharmacy;

    public static ManagePharmacyFragment newInstance(Bundle bundle) {
        ManagePharmacyFragment fragment = new ManagePharmacyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        managePharmacyPresenter = new ManagePharmacyPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View viewRoot = inflater.inflate(R.layout.fragment_manage_pharmacy, container, false);

        edtAddress = (EditText)viewRoot.findViewById(R.id.edtAddress);
        edtCif = (EditText)viewRoot.findViewById(R.id.edtCif);
        edtEmail = (EditText)viewRoot.findViewById(R.id.edtEmailPharma);
        edtPhone = (EditText)viewRoot.findViewById(R.id.edtPhone);

        btnAdd = (Button)viewRoot.findViewById(R.id.btnAddPharma);

        if (getArguments() != null) {
            pharmacy = getArguments().getParcelable(IPharmacy.KEY);

            edtAddress.setText(pharmacy.getAddress());
            edtPhone.setText(pharmacy.getPhone());
            edtEmail.setText(pharmacy.getEmail());
            edtCif.setText(pharmacy.getCif());

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatePharamcy(pharmacy);
                }
            });
        } else {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    savePharmacy();
                }
            });
        }

        return viewRoot;
    }

    private void savePharmacy() {
        Pharmacy pharma = new Pharmacy(edtAddress.getText().toString(), edtCif.getText().toString(), edtEmail.getText().toString(), edtPhone.getText().toString());

        managePharmacyPresenter.savePharmacy(pharma);
    }

    private void updatePharamcy(Pharmacy pharmacy) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
