package id.ac.week10;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    public static final String EXTRA_MHS_LIST = "EXTRA_MHS_LIST";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rvMhs;
    ArrayList<Mahasiswa> arrMhs=new ArrayList<>();
    AppDatabase db;
    MahasiswaAdapter adapter;
    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = AppDatabase.getAppDatabase(getActivity());
        rvMhs  =  view.findViewById(R.id.recyclerView_mhs);
        rvMhs.setHasFixedSize(true);
        rvMhs.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter= new MahasiswaAdapter(arrMhs);
        rvMhs.setAdapter(adapter);
        new LoadMahasiswaTask().execute(); //panggil load data Mahasiswa dari database
    }
                                                    //input, progress, outout
    private class LoadMahasiswaTask extends AsyncTask<Void,Void, List<Mahasiswa>> {
        @Override
        protected List<Mahasiswa> doInBackground(Void... voids) {
            return db.mahasiswaDAO().getAllMhs();
        }
        @Override
        protected void onPostExecute(List<Mahasiswa> mahasiswas) { //mahasiswas adalah hasil doInBackground
            super.onPostExecute(mahasiswas);
            //selesai load data mahasiswa
            arrMhs.clear();
            arrMhs.addAll(mahasiswas);
            adapter.notifyDataSetChanged();
        }
    }
}