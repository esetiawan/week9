package id.ac.week10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    ArrayList<Mahasiswa> data;

    public MahasiswaAdapter(ArrayList<Mahasiswa> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mhs,parent,false);
        MahasiswaViewHolder mvh = new MahasiswaViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position) {
        Mahasiswa m = data.get(position);
        holder.bind(m);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView  tvNrp, tvNama, tvMajor;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.imageView_photo);
            tvNrp = itemView.findViewById(R.id.textView_nrp);
            tvNama= itemView.findViewById(R.id.textView_name);
            tvMajor = itemView.findViewById(R.id.textView_major);
        }
        void bind(Mahasiswa m) {
            int photo = R.drawable.ic_boy;
            if (m.getGender().equals("Female"))
            {
                photo = R.drawable.ic_girl;
            }
            ivPhoto.setImageResource(photo);
            tvNrp.setText(String.valueOf(m.getNrp()));
            tvNama.setText(m.getName());
            tvMajor.setText(m.getMajor());
        }
    }
}
