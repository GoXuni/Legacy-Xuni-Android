package com.grapecity.xuni.samples.flexgrid;

class CustomCellPoint
{

	private String first;
	private String last;
	private int performanceGauge;

	public CustomCellPoint(String first, String last, int performanceGauge)
	{
		super();
		this.first = first;
		this.last = last;
		this.performanceGauge = performanceGauge;
	}

	public String getFirst()
	{
		return first;
	}

	public void setFirst(String first)
	{
		this.first = first;
	}

	public String getLast()
	{
		return last;
	}

	public void setLast(String last)
	{
		this.last = last;
	}

	public int getPerformanceGauge()
	{
		return performanceGauge;
	}

	public void setPerformanceGauge(int performanceGauge)
	{
		this.performanceGauge = performanceGauge;
	}
}