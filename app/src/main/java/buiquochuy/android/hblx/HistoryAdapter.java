package buiquochuy.android.hblx;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

class HistoryItem {

    private String fullName;
    public HistoryItem( String fullName) {
        this.fullName = fullName;

    }
    public HistoryItem() {
        this.fullName="No name";
    }

    public String getFullName() {
        return this.fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

public  class HistoryAdapter extends ArrayAdapter<HistoryItem> {

    private Activity context;
    public HistoryAdapter(Activity context, int layoutID, List<HistoryItem>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

            convertView =
                    LayoutInflater.from(context).inflate(R.layout.listview_item, null,
                            false);

        HistoryItem historyItem = getItem(position);
        TextView tvFullName = (TextView)
                convertView.findViewById(R.id.tv_person_name);
        tvFullName.setText(historyItem.getFullName());
// Get item
        return convertView;
    }
}
