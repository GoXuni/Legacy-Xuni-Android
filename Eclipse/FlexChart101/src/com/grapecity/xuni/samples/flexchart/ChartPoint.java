/**
 *
 */
package com.grapecity.xuni.samples.flexchart;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import com.grapecity.xuni.core.ObservableList;

/**
 * A class that encapsulates and manipulates series data
 * 
 * @author GrapeCity
 */
public class ChartPoint implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected Date date;
	protected int sales;
	protected int expenses;
	protected int downloads;
	protected int high;
	protected int low;
	protected int open;
	protected int close;
	protected int count;
	protected double sine;
	protected double cosine;
	protected String month;
	protected double precipitation;;
	protected int temperature;
	protected char letter;

	public ChartPoint(int count, double d, double e)
	{
		super();
		this.count = count;
		this.sine = d;
		this.cosine = e;
	}

	public ChartPoint(double sine)
	{
		super();
		this.sine = sine;
	}

	public ChartPoint(String name, int sales, int expenses, int downloads)
	{
		super();
		this.name = name;
		this.sales = sales;
		this.expenses = expenses;
		this.downloads = downloads;
	}

	public ChartPoint(char letter, int count)
	{
		super();
		this.count = count;
		this.letter = letter;
	}

	public ChartPoint(int high, int low, int open, int close, Date date)
	{
		super();
		this.date = date;
		this.high = high;
		this.low = low;
		this.open = open;
		this.close = close;
	}

	public ChartPoint(int volume, int high, int low)
	{
		super();
		this.sales = volume;
		this.high = high;
		this.low = low;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getSales()
	{
		return sales;
	}

	public void setSales(int sales)
	{
		this.sales = sales;
	}

	public int getExpenses()
	{
		return expenses;
	}

	public void setExpenses(int expenses)
	{
		this.expenses = expenses;
	}

	public int getDownloads()
	{
		return downloads;
	}

	public void setDownloads(int downloads)
	{
		this.downloads = downloads;
	}

	public int getHigh()
	{
		return high;
	}

	public void setHigh(int high)
	{
		this.high = high;
	}

	public int getLow()
	{
		return low;
	}

	public void setLow(int low)
	{
		this.low = low;
	}

	public int getOpen()
	{
		return open;
	}

	public void setOpen(int open)
	{
		this.open = open;
	}

	public int getClose()
	{
		return close;
	}

	public void setClose(int close)
	{
		this.close = close;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public double getSine()
	{
		return sine;
	}

	public void setSine(double sine)
	{
		this.sine = sine;
	}

	public double getCosine()
	{
		return cosine;
	}

	public void setCosine(double cosine)
	{
		this.cosine = cosine;
	}

	public ChartPoint(String month, double precipitation, int temperature)
	{
		super();
		this.month = month;
		this.precipitation = precipitation;
		this.temperature = temperature;
	}

	// a method to create a list of random objects of type ChartPoint
	public static ObservableList<ChartPoint> getList()
	{
		ObservableList<ChartPoint> list = new ObservableList<ChartPoint>();

		int n = 6; // number of series elements
		String[] countries =
		{ "US", "Germany", "UK", "Japan", "Italy", "Greece", "India", "Canada" };
		Random random = new Random();

		for (int i = 0; i < n; i++)
		{
			list.add(new ChartPoint(countries[i], random.nextInt(20000), random.nextInt(20000), random.nextInt(20000)));
		}
		return list;
	}

	/**
	 * a method to create a list of random objects of type ChartPoint with a fixed element size;
	 * 
	 * @param size
	 *            - size of element of series.
	 * */
	public static ObservableList<ChartPoint> getList(int size)
	{
		ObservableList<ChartPoint> list = new ObservableList<ChartPoint>();

		Random random = new Random();

		for (int i = 0; i < size; i++)
		{

			list.add(new ChartPoint(i + "", random.nextInt(20000), random.nextInt(20000), random.nextInt(20000)));
		}
		return list;
	}
}
