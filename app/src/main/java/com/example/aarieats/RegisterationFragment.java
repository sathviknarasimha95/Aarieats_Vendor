package com.example.aarieats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    private Button mCancelBtn;

    private Button mRegisterBtn;

    private EditText email;

    private EditText password;

    private EditText username;

    private EditText phno;

    private EditText address;

    private EditText state;

    private EditText city;

    private OnFragmentInteractionListener mListener;

    public RegisterationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterationFragment newInstance(String param1, String param2) {
        RegisterationFragment fragment = new RegisterationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registeration, container, false);
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCancelBtn = view.findViewById(R.id.cancel);
        mRegisterBtn = view.findViewById(R.id.next);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        username = view.findViewById(R.id.name);
        phno = view.findViewById(R.id.phno);
        address = view.findViewById(R.id.address);
        city = view.findViewById(R.id.city);
        state = view.findViewById(R.id.state);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.my_nav_host_fragment);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.cancelRegister);
            }
        });
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getInputFromText();
                if(extras!=null) {
                    navController.navigate(R.id.getLocationAction, extras);
                } else {
                    Toast.makeText(getActivity(),"Please enter all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private Bundle getInputFromText() {
        Bundle extras = new Bundle();
        if(email.getText().toString().length() > 0 && password.getText().toString().length() > 0 && phno.getText().toString().length() > 0 && username.getText().toString().length() > 0 && address.getText().toString().length() > 0 && city.getText().toString().length() > 0 && state.getText().toString().length() > 0) {
            extras.putString("email", email.getText().toString());
            extras.putString("password", password.getText().toString());
            extras.putString("name", username.getText().toString());
            extras.putString("phno",phno.getText().toString());
            extras.putString("address", address.getText().toString());
            extras.putString("city", city.getText().toString());
            extras.putString("state", state.getText().toString());
            return extras;
        }
        return null;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void sendData(String name);
    }
}
