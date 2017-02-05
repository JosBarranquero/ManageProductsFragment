package com.barranquero.manageproductsrecycler;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.barranquero.manageproductsrecycler.adapter.PharmacyAdapter;
import com.barranquero.manageproductsrecycler.database.ManageProductContract;
import com.barranquero.manageproductsrecycler.interfaces.PharmacyPresenter;
import com.barranquero.manageproductsrecycler.presenter.PharmacyPresenterImpl;

/**
 * Created by usuario on 30/01/17
 * ManageProductsFragment
 */

public class ListPharmacyFragment extends Fragment implements PharmacyPresenter.View {
    private PharmacyPresenterImpl mPresenter;
    private PharmacyAdapter mAdapter;
    private FloatingActionButton fabAdd;
    private ListPharmacyListener mCallback;
    private ListView lstPharma;

    public interface ListPharmacyListener {
        void showManagePharmacy(Bundle bundle);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListPharmacyListener)activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage() + " activity must implement ListPharmacyListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new PharmacyPresenterImpl(this);
        mAdapter = new PharmacyAdapter(getActivity(), /*R.layout.item_list_pharmacy, */null,/* ManageProductContract.PharmacyEntry.ALL_COLUMNS, new int[]{R.id.txvCif, R.id.txvAddress, R.id.txvPhone, R.id.txvEmail},*/ 1);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_pharmacy, container, false);

        lstPharma = (ListView) rootView.findViewById(R.id.lstPharma);
        fabAdd = (FloatingActionButton) rootView.findViewById(R.id.fabAddPharma);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lstPharma.setAdapter(mAdapter);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.showManagePharmacy(null);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getAllPharmacy(mAdapter);
    }

    @Override
    public void setCursorPharmacy(Cursor cursor) {
        mAdapter.changeCursor(cursor);
    }
}
