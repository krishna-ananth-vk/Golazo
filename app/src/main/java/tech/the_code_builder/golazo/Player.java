package tech.the_code_builder.golazo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class Player  extends ArrayAdapter {


    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> dept = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();



    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView name,team,pos;

    String uid,d;
    private ArrayList<String> posi = new ArrayList<>();
    Activity context;


    Player(Activity context, ArrayList<String> name, ArrayList<String> dept, ArrayList<String> pos, ArrayList<String> id,ArrayList<String> list){
        super(context,R.layout.createteamlist,name);

        this.uid = uid;
        this.context = context;
        this.posi = pos;
        this.names = name;
        this.dept = dept;
        this.id = id;
        this.list = list;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.createteamlist,null,true);


        final DocumentReference docRef = db.collection("company").document(this.id.get(position));
        name = rowView.findViewById(R.id.pname);

        pos = rowView.findViewById(R.id.pos);

        d = id.get(position);
        final  TextView select =  rowView.findViewById(R.id.select);
        if (list.contains(d)){
            select.setText("Remove");
        }
        else {

            select.setText("Add");

        }

        team = rowView.findViewById(R.id.team);
        name.setText(this.names.get(position));
        pos.setText(this.posi.get(position));
        team.setText(this.dept.get(position));






        return rowView;
    }


}
