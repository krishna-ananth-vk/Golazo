//package tech.the_code_builder.golazo;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class PlayerAdapter extends ArrayAdapter<Player> {
//    private ArrayList<Player> dataset;
//    private Context context;
//    String sname,sdept,spos;
//    ArrayList<String> pid,pname;
//
//
//    int civil=0;
//    int cse=0;
//    int mech=0;
//    int barch=0;
//    int ec=0;
//    int eee=0;
//    int b = 0;
//    int f = 0;
//    int m = 0;
//
//    public static class ViewHolder{
//        TextView name;
//        TextView pos;
//        TextView team;
//        Button select;
//    }
//
//    public PlayerAdapter(ArrayList<Player> data, Context context){
//        super(context, R.layout.createteamList, data);
//        this.dataset = data;
//        this.context = context;
//    }
//
//
//
//    int lastposition = -1;
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        final Player player = getItem(position);
//
//        final PlayerAdapter.ViewHolder viewHolder;
//
//        final View result;
//
//
//        pid = new ArrayList<>();
//        pname = new ArrayList<>();
//        if(convertView == null){
//            viewHolder = new PlayerAdapter.ViewHolder();
//
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.createteamList,parent,false);
//            viewHolder.name = (TextView)convertView.findViewById(R.id.pname);
//
//            viewHolder.pos = convertView.findViewById(R.id.pos);
//
//            viewHolder.select = convertView.findViewById(R.id.select);
//
//            viewHolder.team = convertView.findViewById(R.id.team);
//            result = convertView;
//            convertView.setTag(viewHolder);
//
//        }
//        else {
//            viewHolder = (PlayerAdapter.ViewHolder) convertView.getTag();
//            result = convertView;
//        }
//
//
//        viewHolder.name.setText(player.getName());
//        viewHolder.pos.setText(player.getPos());
//        viewHolder.team.setText(player.getD());
//
//        viewHolder.select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sname = player.getId();
//                sdept = player.getD();
//                spos = player.getPos();
//
//                if (pid.contains(sname)){
//                    Toast.makeText(getContext(),"Player removed",Toast.LENGTH_LONG).show();
//                    pid.remove(sname);
//                    viewHolder.select.setText("Add");
//
//                    switch (sdept){
//                        case "eee":
//                            eee--;
//                            pid.remove(sname);
//
//                            break;
//                        case "ece":
//                            ec--;
//                            pid.remove(sname);
//
//                            break;
//                        case "civil":
//                            civil--;
//                            pid.remove(sname);
//                            break;
//                        case "cse":
//                            cse--;
//                            pid.remove(sname);
//
//                            break;
//                        case "mech": mech--;
//                            pid.add(sname);
//
//                            break;
//
//                        case "barch": barch--;
//                            pid.add(sname);
//                            break;
//                    }
////
//
//                }
//
//
//                else {
//
//                    viewHolder.select.setText("Remove");
//                    switch (sdept){
//                        case "eee":
//
//                            eee++;
//                            if(eee>3){
//
//                                Log.d("player - ",""+eee);
//                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                pid.add(sname);
//                            }
//                            break;
//                        case "ece":
//
//                            ec++;
//                            if(ec>3){
//                                Log.d("player - ",""+ec);
//                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                pid.add(sname);
//                            }
//                            break;
//                        case "civil": civil++;
//
//                            if(civil>3){
//                                Log.d("player - ",""+civil);
//                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                pid.add(sname);
//                            }
//                            break;
//                        case "cse": cse++;
//
//                            if(cse>3){
//                                Log.d("player - ",""+cse);
//                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                pid.add(sname);
//                            }
//                            break;
//                        case "mech": mech++;
//
//                            if(mech>3){
//                                Log.d("player - ",""+mech);
//                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                pid.add(sname);
//                            }
//                            break;
//
//                        case "barch": barch++;
//                            if(barch>3){
//                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
//                            }
//                            else {
//                                pid.add(sname);
//
//                            }
//                            break;
//                    }
//
//                }
//
//
//
//            }
//        });
//
////        viewHolder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
////                sname = player.getId();
////                sdept = player.getD();
////                spos = player.getPos();
////                if(b){
////                    Log.d("player",sname);
////                    switch (sdept){
////                        case "eee":
////
////                            eee++;
////                            if(eee>3){
////                                compoundButton.setChecked(false);
////                                Log.d("player - ",""+eee);
////                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
////                            }
////                            else {
////                                pid.add(sname);
////                            }
////                            break;
////                        case "ece":
////
////                            ec++;
////                            if(ec>3){
////                                Log.d("player - ",""+ec);
////                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
////                            }
////                            else {
////                                pid.add(sname);
////                            }
////                            break;
////                        case "civil": civil++;
////
////                            if(civil>3){
////                                Log.d("player - ",""+civil);
////                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
////                            }
////                            else {
////                                pid.add(sname);
////                            }
////                            break;
////                        case "cse": cse++;
////
////                            if(cse>3){
////                                Log.d("player - ",""+cse);
////                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
////                            }
////                            else {
////                                pid.add(sname);
////                            }
////                            break;
////                        case "mech": mech++;
////
////                            if(mech>3){
////                                Log.d("player - ",""+mech);
////                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
////                            }
////                            else {
////                                pid.add(sname);
////                            }
////                            break;
////
////                        case "barch": barch++;
////                            if(barch>3){
////                                Toast.makeText(getContext(),"Player limit exceeded. You can select atmost 3 players from a department",Toast.LENGTH_LONG).show();
////                            }
////                            else {
////                                pid.add(sname);
////
////                            }
////                            break;
////                    }
////                }
////                else {
////
////                    switch (sdept){
////                        case "eee":
////                            eee--;
////                            pid.remove(sname);
////
////                            break;
////                        case "ece":
////                            ec--;
////                            pid.remove(sname);
////
////                            break;
////                        case "civil":
////                            civil--;
////                            pid.remove(sname);
////                            break;
////                        case "cse":
////                            cse--;
////                            pid.remove(sname);
////
////                            break;
////                        case "mech": mech--;
////                            pid.add(sname);
////
////                            break;
////
////                        case "barch": barch--;
////                            pid.add(sname);
////                            break;
////                    }
////
////
////
////                }
////            }
////        });
//
//        return convertView;
//    }
//
//
//    public void showalert(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//
//        builder.setTitle("Player limit exceeded");
//        builder.setMessage("You can select atmost 3 players from a team");
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(getContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
//            }
//        });
//        builder.setCancelable(false);
//        builder.show();
//
//    }
//}
//
