package com.hemaapp.hm_gtsdp.nettask;

import java.util.HashMap;

import org.json.JSONObject;

import xtom.frame.exception.DataParseException;

import com.hemaapp.hm_FrameWork.result.HemaArrayResult;
import com.hemaapp.hm_gtsdp.GtsdpHttpInformation;
import com.hemaapp.hm_gtsdp.GtsdpNetTask;
import com.hemaapp.hm_gtsdp.model.FileUploadResult;

/**
 * 上传文件（图片，音频，视频）
 */
public class FileUploadTask extends GtsdpNetTask {

	public FileUploadTask(GtsdpHttpInformation information,
			HashMap<String, String> params) {
		super(information, params);
	}

	public FileUploadTask(GtsdpHttpInformation information,
			HashMap<String, String> params, HashMap<String, String> files) {
		super(information, params, files);
	}

	@Override
	public Object parse(JSONObject jsonObject) throws DataParseException {
		return new Result(jsonObject);
	}

	private class Result extends HemaArrayResult<FileUploadResult> {

		public Result(JSONObject jsonObject) throws DataParseException {
			super(jsonObject);
		}

		@Override
		public FileUploadResult parse(JSONObject jsonObject)
				throws DataParseException {
			return new FileUploadResult(jsonObject);
		}

	}
}
