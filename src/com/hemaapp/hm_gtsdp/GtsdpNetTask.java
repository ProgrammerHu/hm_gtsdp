package com.hemaapp.hm_gtsdp;

import java.util.HashMap;

import com.hemaapp.hm_FrameWork.HemaNetTask;

/**
 * ������������
 */
public abstract class GtsdpNetTask extends HemaNetTask {
	/**
	 * ʵ����������������
	 * 
	 * @param information
	 *            ����������Ϣ
	 * @param params
	 *            ���������(������,����ֵ)
	 */
	public GtsdpNetTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		this(information, params, null);
	}

	/**
	 * ʵ����������������
	 * 
	 * @param information
	 *            ����������Ϣ
	 * @param params
	 *            ���������(������,����ֵ)
	 * @param files
	 *            �����ļ���(������,�ļ��ı���·��)
	 */
	public GtsdpNetTask(GtsdpHttpInformation information,
			HashMap<String, String> params, HashMap<String, String> files) {
		super(information, params, files);
	}

	@Override
	public GtsdpHttpInformation getHttpInformation() {
		return (GtsdpHttpInformation) super.getHttpInformation();
	}

}
