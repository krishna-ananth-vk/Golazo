package tech.the_code_builder.golazo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User>  {
    private ArrayList<User> dataset;
    private Context context;

    public static class ViewHolder{
        TextView name;
        TextView pos;
        TextView points;

    }

    public UserAdapter(ArrayList<User> data, Context context){
        super(context, R.layout.leaderlist, data);
        this.dataset = data;
        this.context = context;
    }



    int lastposition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        User user = getItem(position);

        ViewHolder viewHolder;

        final View result;

        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.leaderlist,parent,false);
            viewHolder.name = (TextView)convertView.findViewById(R.id.userName);
            viewHolder.points = (TextView)convertView.findViewById(R.id.userPoints);
            viewHolder.pos = convertView.findViewById(R.id.userPos);

            result = convertView;
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.points.setText(user.getPoints());
        viewHolder.name.setText(user.getName());
        viewHolder.pos.setText(""+position);

        return convertView;
    }
}

