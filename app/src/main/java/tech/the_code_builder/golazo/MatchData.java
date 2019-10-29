package tech.the_code_builder.golazo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MatchData extends ArrayAdapter {

    Activity context;
    ArrayList<String> name;
    ArrayList<String> dept;
    ArrayList<String> pos;
    ArrayList<String> point;

    String p;

    TextView pname,ppos,team,pointview;

    MatchData(Activity context, ArrayList<String> name, ArrayList<String> dept, ArrayList<String> pos,ArrayList<String> point ){
        super(context,R.layout.createteamlist,name);

        this.context = context;

        this.pos = pos;
        this.name = name;
        this.dept = dept;
        this.point = point;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.teamlistitem,null,true);

        pname = rowView.findViewById(R.id.name);
        ppos = rowView.findViewById(R.id.pos);
        team = rowView.findViewById(R.id.dept);
        pointview = rowView.findViewById(R.id.point);


        p = this.pos.get(position);
        System.out.println(p);
        if (p.equals("b")){
            ppos.setText("Defender");
        }
        else if (p.equals("m")){
            ppos.setText("Midfielder");
        }
        else if (p.equals("f")){
            ppos.setText("Forward");
        }
        else if (p.equals("gk")){
            ppos.setText("GoalKeeper");
        }
        else {
            ppos.setText("none");
        }
        pname.setText(this.name.get(position));



        team.setText(this.dept.get(position));
        return rowView;
    }
}

