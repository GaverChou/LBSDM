package com.gaver.dbscan;

public class DataPoint {
	private String dataPointName; // ��������
    private double dimensioin[]; // �������ά��
    private boolean isKey; //�Ƿ��Ǻ��Ķ���

    public DataPoint(){

    }

    public DataPoint(double[] dimensioin,String dataPointName,boolean isKey){
         this.dataPointName=dataPointName;
         this.dimensioin=dimensioin;
         this.isKey=isKey;
    }

	public String getDataPointName() {
		return dataPointName;
	}

	public void setDataPointName(String dataPointName) {
		this.dataPointName = dataPointName;
	}

	public double[] getDimensioin() {
		return dimensioin;
	}

	public void setDimensioin(double[] dimensioin) {
		this.dimensioin = dimensioin;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
    
    

}