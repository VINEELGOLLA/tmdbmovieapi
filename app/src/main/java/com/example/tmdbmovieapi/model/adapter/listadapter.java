package com.example.tmdbmovieapi.model.adapter;
import com.example.tmdbmovieapi.R;
import com.example.tmdbmovieapi.model.pojo.searchname;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class listadapter extends ArrayAdapter<searchname> {


    public listadapter(@NonNull Context context, int resource,@NonNull  List<searchname> objects) {
        super(context,resource, objects);

    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        searchname searchname = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewitem, parent, false);
        }

        TextView name = convertView.findViewById(R.id.itemname);
        name.setText(searchname.getName());

        return convertView;
    }

}
