package tech.the_code_builder.golazo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ConfirmData  extends ArrayAdapter {


    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> names;
    private ArrayList<String> dept;
    private ArrayList<String> list = new ArrayList<>();



    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private TextView name,team,pos;


    private ArrayList<String> posi;
    private Activity context;


    ConfirmData(Activity context, ArrayList<String> name, ArrayList<String> dept, ArrayList<String> pos){
        super(context,R.layout.createteamlist,name);

        this.context = context;
        this.posi = pos;
        this.names = name;
        this.dept = dept;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.confirmistitem,null,true);



        name = rowView.findViewById(R.id.name);

        pos = rowView.findViewById(R.id.pos);

        team = rowView.findViewById(R.id.dept);


        name.setText(this.names.get(position));
        pos.setText(this.posi.get(position));
        team.setText(this.dept.get(position));






        return rowView;
    }


}

