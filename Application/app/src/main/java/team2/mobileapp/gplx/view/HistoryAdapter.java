package team2.mobileapp.gplx.view;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.models.HistoricalExam;

public class HistoryAdapter extends ArrayAdapter<HistoricalExam> {
    private LinearLayout historyLayout;
    private TextView tvLicenseSet;
    private TextView tvDate;
    private TextView tvCorrect;
    private TextView tvTotal;
    private Activity context;

    public HistoryAdapter(Activity context, int layoutID, List<HistoricalExam>
            objects) {
        super(context, layoutID, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView =
                LayoutInflater.from(context).inflate(R.layout.listview_history_item, null,
                        false);

        tvLicenseSet = (TextView) convertView.findViewById(R.id.tv_license_set_history);
        tvDate = (TextView) convertView.findViewById(R.id.tv_date_history);
        tvCorrect = (TextView) convertView.findViewById(R.id.tv_correct_history);
        tvTotal = (TextView) convertView.findViewById(R.id.tv_total_history);
        HistoricalExam historyItem = getItem(position);
        tvLicenseSet.setText("Hạng " + historyItem.getLicense() + " - " + historyItem.getSetname());
        tvDate.setText(historyItem.getDate());
        tvCorrect.setText(String.valueOf(historyItem.getCorrect()));
        tvTotal.setText(String.valueOf(historyItem.getTotal()));

        historyLayout = (LinearLayout) convertView.findViewById(R.id.layout_history);

        switch (historyItem.getLicense()) {
            case "A1":
            case "A2":
                if (historyItem.getCorrect() < 23)
                    historyLayout.setBackgroundTintList(ColorStateList.valueOf(convertView.getResources().getColor(R.color.failure)));
                break;
            case "B1":
                if (historyItem.getCorrect() < 28)
                    historyLayout.setBackgroundTintList(ColorStateList.valueOf(convertView.getResources().getColor(R.color.failure)));
                break;
            case "B2":
                if (historyItem.getCorrect() < 32)
                    historyLayout.setBackgroundTintList(ColorStateList.valueOf(convertView.getResources().getColor(R.color.failure)));
                break;
        }
        return convertView;
    }
}