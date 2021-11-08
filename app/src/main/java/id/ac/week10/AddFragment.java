package id.ac.week10;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    AppDatabase db;
    EditText etNrp,etNama;
    RadioGroup rg;
    Spinner spMajor;
    RadioButton rbMale;
    Button btnAdd;
    private OnFragmentInteractionListener myListener;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = AppDatabase.getAppDatabase(getActivity());

        etNrp = view.findViewById(R.id.editText_nrp);
        etNama = view.findViewById(R.id.editText_name);
        rg = view.findViewById(R.id.radioGroup_gender);
        rbMale = view.findViewById(R.id.radioButton_male);
        spMajor = view.findViewById(R.id.spinner_major);
        btnAdd = view.findViewById(R.id.button_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mahasiswa m = new Mahasiswa();
                m.setName(etNama.getText().toString());
                m.setNrp(Integer.parseInt(etNrp.getText().toString()));
                int selected = rg.getCheckedRadioButtonId();
                RadioButton rb = view.getRootView().findViewById(selected);
                m.setGender(rb.getText().toString());
                m.setMajor(spMajor.getSelectedItem().toString());
                new AddMahasiswaTask().execute(m); //m adalah data mahasiswa baru
            }
        });
    }
    private class AddMahasiswaTask extends AsyncTask<Mahasiswa,Void,Void>
    {
        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            db.mahasiswaDAO().insert(mahasiswas[0]); //ini ambil data yang ke 0
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            reset();
            Toast.makeText(getActivity(),"Data berhasil diInsert",Toast.LENGTH_LONG).show();
        }
    }

    private void reset()
    {
        etNrp.setText("");
        etNama.setText("");
        rbMale.setChecked(true);
        spMajor.setSelection(0);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //memastikan Activity utama sudah implements OnFragmentInteractionListener
        if (context instanceof OnFragmentInteractionListener)
        {
            myListener = (OnFragmentInteractionListener)context;
        }
        else {
            throw new RuntimeException(context.toString()+" harus implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myListener=null; //reset
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Mahasiswa m);
    }
}