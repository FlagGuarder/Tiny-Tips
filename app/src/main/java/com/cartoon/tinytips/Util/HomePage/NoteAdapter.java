package com.cartoon.tinytips.Util.HomePage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cartoon.tinytips.Activity.HomePage.HomePageNoteDetails;
import com.cartoon.tinytips.R;
import com.cartoon.tinytips.Util.GetContext;
import com.cartoon.tinytips.data.Note;

import java.util.List;

/**
 * Created by cartoon on 2017/11/21.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private List<Note> noteList;

    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View noteView;
        CardView cardView;
        TextView title;
        TextView wordDetails;
        ImageView imageDetails;
        TextView classify1;
        TextView classify2;
        TextView classify3;
        TextView date;
        public ViewHolder(View view){
            super(view);
            initView(view);  //实例化控件
        }
        private void initView(View view){
            noteView=view;
            cardView=(CardView)view;
            title=(TextView)view.findViewById(R.id.homePageNoteTitle);
            wordDetails=(TextView)view.findViewById(R.id.homePageNoteWordDetails);
            imageDetails=(ImageView) view.findViewById(R.id.homePageNoteImageDetails);
            classify1=(TextView)view.findViewById(R.id.homePageNoteClassify1);
            classify2=(TextView)view.findViewById(R.id.homePageNoteClassify2);
            classify3=(TextView)view.findViewById(R.id.homePageNoteClassify3);
            date=(TextView)view.findViewById(R.id.homePageNoteDate);
        }
    }
    public NoteAdapter(List<Note> noteList){
        this.noteList=noteList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.homepage_note_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.noteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Note note=noteList.get(position);
                /*Intent intent=new Intent(GetContext.getContext(), HomePageNoteDetails.class);
                Activity activity=(Activity)context;
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) context).startActivityForResult(intent,1);
                activity.finish();*/
                Intent intent=new Intent(GetContext.getContext(), HomePageNoteDetails.class);
                Activity activity=(Activity)context;
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) context).startActivity(intent);
                activity.finish();
            }
        });
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Note note=noteList.get(position);
                Intent intent=new Intent(GetContext.getContext(), HomePageNoteDetails.class);
                Activity activity=(Activity)context;
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) context).startActivityForResult(intent,1);
                activity.finish();
            }
        });
        holder.wordDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Note note=noteList.get(position);
            }
        });
        holder.imageDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Note note=noteList.get(position);
            }
        });
        holder.classify1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Note note=noteList.get(position);
            }
        });
        holder.classify2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Note note=noteList.get(position);
            }
        });
        holder.classify3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position=holder.getAdapterPosition();
                Note note=noteList.get(position);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Note note=noteList.get(position);
        holder.title.setText(note.getTitle());
        holder.wordDetails.setText(note.getWordDetails());
        Glide.with(context).load(note.getImageDetails1()).into(holder.imageDetails);
        holder.classify1.setText(note.getClassify1());
        holder.classify2.setText(note.getClassify2());
        holder.classify3.setText(note.getClassify3());
        holder.date.setText(note.getDate());
    }
    @Override
    public int getItemCount(){
        return noteList.size();
    }
}
