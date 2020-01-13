package com.example.zhasanguo.person;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zhasanguo.R;
import com.example.zhasanguo.activity.PersonsActivity;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private Context mContext;
    private List<Person> mPersonList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView personImage;
        View personView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
            personImage = itemView.findViewById(R.id.person_image1);
            Log.d("ID", Integer.toString(R.id.person_image1));
        }
    }
    public  PersonAdapter(List<Person> personList){
        mPersonList = personList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         if (mContext == null){
             mContext = parent.getContext();
         }
         View view = LayoutInflater.from(mContext).inflate(R.layout.person_item,parent,false);
         final  ViewHolder holder = new ViewHolder(view);
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 int position = holder.getAdapterPosition();
                 Person person = mPersonList.get(position);
                 Intent intent = new Intent(mContext, PersonsActivity.class);
                 //intent.putExtra(PersonsActivity.PERSON_IMAGE_ID, person.getImageId());
                 intent.putExtra("image_name", person.getImageName());
                 mContext.startActivity(intent);
             }
         });

        return holder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person person = mPersonList.get(position);
        Glide.with(mContext).load(person.getImageId()).into(holder.personImage);
    }



    @Override
    public int getItemCount() {
        return mPersonList.size();
    }
}
