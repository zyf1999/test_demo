package com.jk.util;
/**
 * 
 * <pre>项目名称：zly    
 * 类名称：EasyuiPage    
 * 类描述：    
 * 创建人：赵雅芳   
 * 创建时间：2019年3月22日 下午8:20:03    
 * 修改人：赵雅芳      
 * 修改时间：2019年3月22日 下午8:20:03    
 * 修改备注：       
 * @version </pre>
 */
public class EasyuiPage {

	private Integer total;
	private Object rows;
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "EasyuiPage [total=" + total + ", rows=" + rows + "]";
	}
	
}
