package com.gaos.tree_view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.gaos.bean.Bean;
import com.gaos.bean.FileBean;
import com.gaos.tree.bean.Node;
import com.gaos.tree_view.R;

public class MainActivity extends Activity {
	private List<FileBean> mDatas = new ArrayList<FileBean>();
	private ListView mTree;
	private SimpleTreeAdapter mAdapter;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initDatas();
		mTree = (ListView) findViewById(R.id.id_tree);
		text = (TextView)findViewById(R.id.text);
		try {
			mAdapter = new SimpleTreeAdapter<FileBean>(mTree, this, mDatas, 10);

		} catch (Exception e) {
			e.printStackTrace();
		}
		mTree.setAdapter(mAdapter);

	}

	/**
	 * 显示勾选的信息
	 * @param view
	 */
	public void Show(View view){
		String result = "";
		List<Node> list = mAdapter.getCheckNode();
		for (int i = 0; i < list.size(); i++) {
			Node node = list.get(i);
			result = result + node.getName() + node.getId() + "\n";
			if(!node.isExpand()){
				for (int j = 0; j < node.getChildren().size(); j++) {
					Node nodeChild = node.getChildren().get(j);
					result = result + nodeChild.getName() + nodeChild.getId() + "\n";
				}
			}
		}
		text.setText(result);
	}
	/**
	 * 从xml中解析数据
	 */
	private void initDatas() {

		InputStream is = null;
		try {
			// 获取读取文件的输入流对象
			is = getAssets().open("org.xml");
			// 采用dom解析
			mDatas = PullParser.parseXML(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
