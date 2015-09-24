package com.hemaapp.hm_gtsdp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hemaapp.hm_FrameWork.HemaNetTask;
import com.hemaapp.hm_FrameWork.result.HemaBaseResult;
import com.hemaapp.hm_gtsdp.GtsdpActivity;
import com.hemaapp.hm_gtsdp.R;
/**
 * ���ð�������
 * @author Wen
 * @author HuFanglin
 *
 */
public class SetQuestionActivty extends GtsdpActivity implements OnClickListener {
    private static final String[] question={"��ѡ��", "����ϲ���Ĳ���ʲô��","����ϲ������ɫ��",
    	"��ְֽ�ʲô���֣�","�������ʲô���֣�","��ļ��������", "��Ĵ�ѧ��ʲô��"};
    
	private ImageView imageQuitActivity;
	private TextView txtTitle, txtNext;
	private Spinner spinner1, spinner2, spinner3;
    private ArrayAdapter<String> adapter;
    private Button btnConfirm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_set_question);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void callAfterDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForGetDataFailed(HemaNetTask arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerFailed(HemaNetTask arg0, HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBackForServerSuccess(HemaNetTask arg0,
			HemaBaseResult arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void callBeforeDataBack(HemaNetTask arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void findView() {
		txtTitle = (TextView)findViewById(R.id.txtTitle);
		txtTitle.setText("���ð�������");
		txtNext = (TextView)findViewById(R.id.txtNext);
		txtNext.setText("�ر�");
		imageQuitActivity = (ImageView) findViewById(R.id.imageQuitActivity);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		// ����ѡ������ArrayAdapter��������
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, question);
		// ���������б�ķ��
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(adapter);
		spinner2.setAdapter(adapter);
		spinner3.setAdapter(adapter);
		btnConfirm = (Button) findViewById(R.id.btnConfirm);
		
	}

	@Override
	protected void getExras() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setListener() {
		imageQuitActivity.setOnClickListener(this);
		txtNext.setOnClickListener(this);
		btnConfirm.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.imageQuitActivity:
		case R.id.txtNext:
			finish(R.anim.none, R.anim.right_out);
			break;
		case R.id.btnConfirm:
			
			break;
		}
		
	}

}
