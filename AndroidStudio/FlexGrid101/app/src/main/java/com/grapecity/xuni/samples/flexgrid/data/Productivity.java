package com.grapecity.xuni.samples.flexgrid.data;

import java.io.Serializable;
import java.util.Random;

import com.grapecity.xuni.core.ObservableList;

public class Productivity implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int q1;
	int q2;
	int q3;
	int q4;
	int q5;
	int q6;
	int q7;
	int q8;

	public static ObservableList<Productivity> getList(int count)
	{
		ObservableList<Productivity> list = new ObservableList<Productivity>();
		for (int i = 0; i < count; i++)
		{
			Productivity productivity = new Productivity();
			Random r = new Random();
			productivity.q1 = r.nextInt(500);
			productivity.q2 = r.nextInt(500);
			productivity.q3 = r.nextInt(500);
			productivity.q4 = r.nextInt(500);
			productivity.q5 = r.nextInt(500);
			productivity.q6 = r.nextInt(500);
			productivity.q7 = r.nextInt(500);
			productivity.q8 = r.nextInt(500);
			list.add(productivity);
		}

		return list;
	}

}
