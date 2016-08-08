package com.marenbo.www.example.sample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marenbo.www.example.R;
import com.marenbo.www.example.utils.ToastUtils;

public class SampleAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    private final String[] mGroups = {
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat"
    };

    private final int[] mGroupDrawables = {
            R.mipmap.cupcake,
            R.mipmap.donut,
            R.mipmap.eclair,
            R.mipmap.froyo,
            R.mipmap.gingerbread,
            R.mipmap.honeycomb,
            R.mipmap.ice_cream_sandwich,
            R.mipmap.jelly_bean,
            R.mipmap.kitkat
    };

    private final String[][] mChilds = {
            {"1.5"},
            {"1.6"},
            {"2.0", "2.0.1", "2.1"},
            {"2.2", "2.2.1", "2.2.2", "2.2.3"},
            {"2.3", "2.3.1", "2.3.2", "2.3.3", "2.3.4", "2.3.5", "2.3.6", "2.3.7"},
            {"3.0", "3.1", "3.2", "3.2.1", "3.2.2", "3.2.3", "3.2.4", "3.2.5", "3.2.6"},
            {"4.0", "4.0.1", "4.0.2", "4.0.3", "4.0.4"},
            {"4.1", "4.1.1", "4.1.2", "4.2", "4.2.1", "4.2.2", "4.3", "4.3.1"},
            {"4.4"}
    };

    public SampleAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return mGroups.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupHolder groupHolder = null;

        if (convertView == null) {

            convertView = mLayoutInflater.inflate(R.layout.sample_activity_list_group_item, parent, false);

            groupHolder = new GroupHolder();

            groupHolder.click1 = (TextView) convertView.findViewById(R.id.click1);

            groupHolder.click2 = (TextView) convertView.findViewById(R.id.click2);

            groupHolder.imageView = (ImageView) convertView.findViewById(R.id.sample_activity_list_group_expanded_image);

            groupHolder.expand = (ImageView) convertView.findViewById(R.id.sample_activity_list_group_item_image);

            groupHolder.text = (TextView) convertView.findViewById(R.id.sample_activity_list_group_item_text);

            convertView.setTag(groupHolder);

        } else {

            groupHolder = (GroupHolder) convertView.getTag();
        }

        groupHolder.expand.setImageResource(mGroupDrawables[groupPosition]);

        groupHolder.expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtils.showToast(mContext, "groupPosition:" + groupPosition);

            }
        });

        groupHolder.text.setText(mGroups[groupPosition]);

        final int resId = isExpanded ? R.mipmap.minus : R.mipmap.plus;

        groupHolder.imageView.setImageResource(resId);


        groupHolder.click1
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ToastUtils.showToast(mContext, "click1  groupPosition = " + groupPosition);

                    }
                });

        groupHolder.click2
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(mContext, "click2  groupPosition = " + groupPosition);

                    }
                });


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChilds[groupPosition].length;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChilds[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.sample_activity_list_child_item, parent, false);
        }

        final TextView text = (TextView) convertView.findViewById(R.id.sample_activity_list_child_item_text);
        text.setText(mChilds[groupPosition][childPosition]);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupHolder {

        private TextView text;

        private ImageView imageView;

        private ImageView expand;

        private TextView click1;

        private TextView click2;

    }

}
