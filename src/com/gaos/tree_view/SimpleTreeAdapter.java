package com.gaos.tree_view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gaos.tree.bean.Node;
import com.gaos.tree.bean.TreeListViewAdapter;
import com.gaos.tree_view.R;

public class SimpleTreeAdapter<T> extends TreeListViewAdapter<T> {

	public SimpleTreeAdapter(ListView mTree, Context context, List<T> datas,
			int defaultExpandLevel) throws IllegalArgumentException,
			IllegalAccessException {
		super(mTree, context, datas, defaultExpandLevel);
	}
	/**
	 * 复选框联动  
	 * @param node
	 * @param isChecked
	 */
    private void checkNode(Node node,boolean isChecked){  
        node.setChecked(isChecked);  
        for(int i=0;i<node.getChildren().size();i++){  
            checkNode(node.getChildren().get(i),isChecked);  
        }  
    }  
    
    /**
     * 获得被选中节点信息
     * @return
     */
    public List<Node> getCheckNode(){
    	List<Node> nodelist = new ArrayList<Node>();
    	for (int i = 0; i < getCount(); i++) {
    		Node node = mNodes.get(i);
    		if(node.isChecked()){
    			nodelist.add(node);
    		}
		}
    	return nodelist;
    }
     
	@Override
	public View getConvertView(final Node node, int position, View convertView,
			ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) convertView
					.findViewById(R.id.id_treenode_icon);
			viewHolder.label = (TextView) convertView
					.findViewById(R.id.id_treenode_label);
			viewHolder.checkbox = (CheckBox) convertView
					.findViewById(R.id.checkbox);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		if (node.getIcon() == -1) {
			viewHolder.icon.setVisibility(View.INVISIBLE);
		} else {
			viewHolder.icon.setVisibility(View.VISIBLE);
			viewHolder.icon.setImageResource(node.getIcon());
		}
		viewHolder.label.setText(node.getName());
		viewHolder.checkbox.setChecked(node.isChecked());

		 // 复选框单击事件  
		 viewHolder.checkbox.setOnClickListener(new OnClickListener(){  

           @Override  
           public void onClick(View v) {  
               // TODO Auto-generated method stub  
//               Node n = (Node)v.getTag();  
               checkNode(node,((CheckBox)v).isChecked());  
               notifyDataSetChanged();  
           }  
       }); 
		return convertView;
	}

	public class ViewHolder {
		ImageView icon;
		TextView label;
		CheckBox checkbox;
	}

}
