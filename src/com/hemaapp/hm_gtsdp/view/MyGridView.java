package com.hemaapp.hm_gtsdp.view;

import xtom.frame.image.load.XtomImageTask;
import xtom.frame.image.load.XtomImageWorker;
import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.GridView;

public class MyGridView extends GridView {
	private SparseArray<SparseArray<XtomImageTask>> tasks = new SparseArray<SparseArray<XtomImageTask>>();
	private XtomImageWorker imageWorker;// ͼƬ������
	public MyGridView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}
	public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public MyGridView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		imageWorker = new XtomImageWorker(context.getApplicationContext());
		
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, 
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	/**
	 * add a imageTask
	 * 
	 * @param position
	 *            the position in the ListView
	 * @param index
	 *            the index of the task in the position
	 * @param task
	 *            the task
	 */
	public void addTask(int position, int index, XtomImageTask task) {
		if (!imageWorker.isThreadControlable()) {
			imageWorker.loadImage(task);
			return;
		}
		// ��Ҫ�첽ִ�е�������ӽ��������
		if (imageWorker.loadImage(task)) {
			SparseArray<XtomImageTask> tasksInPosition = tasks.get(position);
			if (tasksInPosition == null) {
				tasksInPosition = new SparseArray<XtomImageTask>();
				tasks.put(position, tasksInPosition);
			}
			tasksInPosition.put(index, task);
		}
	}
	

}
