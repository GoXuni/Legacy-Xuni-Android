/**
 *
 */
package com.grapecity.xuni.samples.flexgrid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.grapecity.xuni.core.*;

/**
 * A class to represent an instance of each row/custom element in the grid
 *
 * @author Vivek Punjabi
 */
public class ChartPoint
{

	protected int id;
	protected String name;
	protected int countryId;
	protected String country;
	protected boolean active;
	protected String first;
	protected String last;
	protected Date hiredDate;
	protected Date hiredTime;
	protected float weight;
	protected String father;
	protected String brother;
	protected String cousin;

	public ChartPoint()
	{
		super();
		this.id = 0;
		this.name = "";
		this.countryId = 0;
		this.country = "";
		this.active = false;
		this.first = "";
		this.last = "";
		this.hiredDate = new Date();
		this.hiredTime = new Date();
		this.weight = 0;
		this.father = "";
		this.brother = "";
		this.cousin = "";
	}

	public ChartPoint(int id, String name, int countryId, String country, boolean active, String first, String last, Date hiredDate, Date hiredTime, float weight, String father,
			String brother, String cousin)
	{
		super();
		this.id = id;
		this.name = name;
		this.countryId = countryId;
		this.country = country;
		this.active = active;
		this.first = first;
		this.last = last;
		this.hiredDate = hiredDate;
		this.hiredTime = hiredTime;
		this.weight = weight;
		this.father = father;
		this.brother = brother;
		this.cousin = cousin;
	}

	public ChartPoint(int id, String first, String last, float weight)
	{
		super();
		this.id = id;
		this.first = first;
		this.last = last;
		this.weight = weight;
	}

	public ChartPoint(String[] data) throws ParseException
	{
		super();
		this.id = Integer.valueOf(data[0]);
		this.name = data[1];
		this.countryId = Integer.valueOf(data[2]);
		this.country = data[3];
		this.active = Boolean.valueOf(data[4]);
		this.first = data[5];
		this.last = data[6];

		this.hiredDate = new SimpleDateFormat("MMM dd yyyy",Locale.getDefault()).parse(data[7]);
		this.hiredTime = new SimpleDateFormat("HH:mm:ss",Locale.getDefault()).parse(data[8]);
		this.weight = Float.valueOf(data[9]);
		this.father = data[10];
		this.brother = data[11];
		this.cousin = data[12];
	}

	public int getCountryId()
	{
		return countryId;
	}

	public void setCountryId(int countryId)
	{
		this.countryId = countryId;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
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

	public Date getHiredDate()
	{
		return hiredDate;
	}

	public void setHiredDate(Date hiredDate)
	{
		this.hiredDate = hiredDate;
	}

	public Date getHiredTime()
	{
		return hiredTime;
	}

	public void setHiredTime(Date hiredTime)
	{
		this.hiredTime = hiredTime;
	}

	public float getWeight()
	{
		return weight;
	}

	public void setWeight(float weight)
	{
		this.weight = weight;
	}

	public String getFather()
	{
		return father;
	}

	public void setFather(String father)
	{
		this.father = father;
	}

	public String getBrother()
	{
		return brother;
	}

	public void setBrother(String brother)
	{
		this.brother = brother;
	}

	public String getCousin()
	{
		return cousin;
	}

	public void setCousin(String cousin)
	{
		this.cousin = cousin;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public static ObservableList<ChartPoint> getList()
	{
		ObservableList<ChartPoint> list = new ObservableList<ChartPoint>();

		int n = 100; // number of series elements
		String[] firstNames =
		{ "Paul", "Ben", "Ted", "Ed", "Dan", "Jack" };
		String[] lastNames =
		{ "Richards", "Neiman", "Evers", "Lehman", "Krause", "Stevens" };
		String[] countries =
		{ "US", "Germany", "UK", "Japan", "Italy", "Greece", "India", "Canada" };
		int m = 6;
		int temp1;
		int temp2;
		int temp3;
		Random random = new Random();
		for (int i = 0; i < n; i++)
		{
			temp1 = random.nextInt(m);
			temp2 = random.nextInt(m);
			temp3 = random.nextInt(m);
			list.add(new ChartPoint(i, (firstNames[temp1] + " " + lastNames[temp2]), temp3, countries[temp3], (random.nextInt(2) == 0) ? false : true,
					firstNames[temp1], lastNames[temp2], new Date(), new Date(), random.nextFloat() * 200, firstNames[random.nextInt(m)], firstNames[random.nextInt(m)],
					firstNames[random.nextInt(m)]));
		}
		return list;
	}

	public String[] toStringArray()
	{
		String[] toArray =
		{ Integer.toString(id), name, Integer.toString(countryId), country, Boolean.toString(active), first, last, hiredDate.toString(), Float.toString(weight),
				father, brother, cousin };
		return toArray;
	}

}
