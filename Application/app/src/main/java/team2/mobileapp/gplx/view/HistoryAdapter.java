package team2.mobileapp.gplx.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.dto.HistoryItem;

class HistoryAdapter extends ArrayAdapter<HistoryItem> {

    private Activity context;
    public HistoryAdapter(Activity context, int layoutID, List<HistoryItem>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView =
                LayoutInflater.from(context).inflate(R.layout.listview_history_item, null,
                        false);

        HistoryItem historyItem = getItem(position);
        TextView tvFullName = (TextView)
                convertView.findViewById(R.id.tv_person_name);
        tvFullName.setText(historyItem.getCategoryName());
// Get item
        return convertView;
    }
}