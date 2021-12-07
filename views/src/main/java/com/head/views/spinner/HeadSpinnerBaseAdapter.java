package com.head.views.spinner;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.head.views.R;

@SuppressWarnings("unused")
public abstract class HeadSpinnerBaseAdapter<T> extends BaseAdapter {

    private final PopUpTextAlignment horizontalAlignment;
    private final SpinnerTextFormatter spinnerTextFormatter;
    private final float textSize;

    private int textColor;
    private int backgroundSelector;

    int selectedIndex;

    HeadSpinnerBaseAdapter(
            Context context,
            int textColor,
            float textSize,
            int backgroundSelector,
            SpinnerTextFormatter spinnerTextFormatter,
            PopUpTextAlignment horizontalAlignment
    ) {
        this.spinnerTextFormatter = spinnerTextFormatter;
        this.backgroundSelector = backgroundSelector;
        this.textColor = textColor;
        this.textSize=textSize;
        this.horizontalAlignment = horizontalAlignment;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        TextView textView;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.spinner_list_item, null);
            textView = convertView.findViewById(R.id.text_view_spinner);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                textView.setBackground(ContextCompat.getDrawable(context, backgroundSelector));
            }
            convertView.setTag(new ViewHolder(textView));
        } else {
            textView = ((ViewHolder) convertView.getTag()).textView;
        }
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        textView.setText(spinnerTextFormatter.format(getItem(position)));
        textView.setTextColor(textColor);

        setTextHorizontalAlignment(textView);

        return convertView;
    }

    private void setTextHorizontalAlignment(TextView textView) {
        switch (horizontalAlignment) {
            case START:
                textView.setGravity(Gravity.START);
                break;
            case END:
                textView.setGravity(Gravity.END);
                break;
            case CENTER:
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
        }
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    void setSelectedIndex(int index) {
        selectedIndex = index;
    }

    public abstract T getItemInDataset(int position);

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract T getItem(int position);

    @Override
    public abstract int getCount();

    static class ViewHolder {
        TextView textView;

        ViewHolder(TextView textView) {
            this.textView = textView;
        }
    }
}
