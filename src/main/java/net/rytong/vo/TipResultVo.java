package net.rytong.vo;

public class TipResultVo {
	private String code;
	private String msg;
	
	public TipResultVo() {
	}

	public TipResultVo(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}