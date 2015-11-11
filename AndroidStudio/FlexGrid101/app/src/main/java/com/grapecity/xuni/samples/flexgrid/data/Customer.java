/**
 *
 */
package com.grapecity.xuni.samples.flexgrid.data;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.grapecity.xuni.core.ObservableList;

/**
 * A class to represent an instance of each row/custom element in the grid
 * 
 * @author GrapeCity
 */
public class Customer implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final String BUNDLE_KEY = "CUSTOMER";
	
	private static final String[] FIRST_NAMES = "Andy,Ben,Charlie,Dan,Ed,Fred,Gil,Herb,Jack,Karl,Larry,Mark,Noah,Oprah,Paul,Quince,Rich,Steve,Ted,Ulrich,Vic,Xavier,Zeb"
			.split(",");
	private static final String[] LAST_NAMES = "Ambers,Bishop,Cole,Danson,Evers,Frommer,Griswold,Heath,Jammers,Krause,Lehman,Myers,Neiman,Orsted,Paulson,Quaid,Richards,Stevens,Trask,Ulam"
			.split(",");
	private static final String[] EMAIL_SERVERS = "gmail,yahoo,outlook,aol".split(",");
	private static final String[] STREET_NAMES = "Main,Broad,Grand,Panoramic,Green,Golden,Park,Fake".split(",");
	private static final String[] STREET_TYPES = "ST,AVE,BLVD".split(",");
	private static final String[] STREET_ORIENTATION = "S,N,W,E,SE,SW,NE,NW".split(",");

	public static Map<String, String[]> COUNTRIES;

	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int countryId;
	private String postalCode;
	private String email;
	private Calendar lastOrderDate;
	private int orderCount;
	private double orderTotal;
	private boolean active;

	public Customer()
	{
		this(randomInt(0, 10000));
	}

	public Customer(int id)
	{
		init();

		this.id = id;
		this.firstName = getRandomString(FIRST_NAMES);
		this.lastName = getRandomString(LAST_NAMES);
		this.address = GetRandomAddress();
		this.countryId = randomInt(0, COUNTRIES.size() - 1);
		this.city = getRandomString((String[]) COUNTRIES.values().toArray()[countryId]);
		this.postalCode = String.valueOf(randomInt(10000, 99999));
		this.email = String.format("%s@%s.com", (firstName.substring(0, 1) + lastName).toLowerCase(Locale.ENGLISH), getRandomString(EMAIL_SERVERS));

		this.lastOrderDate = GregorianCalendar.getInstance();
		this.lastOrderDate.add(Calendar.DAY_OF_YEAR, -randomInt(0, 365));
		this.lastOrderDate.add(Calendar.HOUR_OF_DAY, randomInt(0, 24));
		this.lastOrderDate.add(Calendar.MINUTE, randomInt(0, 60));

		this.orderCount = randomInt(0, 100);
		this.orderTotal = Math.random() * 10000.0d;
		this.active = Math.random() >= .5d;
	}

	public Customer(String[] data) throws ParseException
	{
		super();

		init();

		this.id = Integer.valueOf(data[0]);
		this.countryId = Integer.valueOf(data[2]);
		// this.country = data[3];
		this.active = Boolean.valueOf(data[4]);
		this.firstName = data[5];
		this.lastName = data[6];
	}

	public static ObservableList<Customer> getList(int count)
	{
		return getList(count, false);
	}
	
	public static ObservableList<Customer> getList(int count, boolean randomId)
	{
		ObservableList<Customer> list = new ObservableList<Customer>();

		for (int i = 0; i < count; i++)
		{
			if(randomId)
			{
				list.add(new Customer());	
			}
			else
			{
				list.add(new Customer(i));	
			}	
		}

		return list;
	}

	public String[] toStringArray()
	{
		String[] toArray =
		{ Integer.toString(id), Integer.toString(countryId), Boolean.toString(active), firstName, lastName };
		return toArray;
	}

	private static void init()
	{
		if (COUNTRIES == null)
		{
			COUNTRIES = new HashMap<String, String[]>();

			COUNTRIES.put("China", "Beijing,Chongqing,Shanghai,Tianjin,Hong Kong,Macau,Anqing,Bengbu,Bozhou,Chaohu".split(","));
			COUNTRIES.put("India", "New Delhi,Mumbai,Delhi,Bangalore,Hyderabad,Ahmedabad,Chennai,Kolkata,Surat,Pune".split(","));
			COUNTRIES.put("United States", "Washington,New York,Los Angeles,Chicago,Houston,Philadelphia,Phoenix,San Antonio,San Diego,Dallas".split(","));
			COUNTRIES.put("Indonesia", "Jakarta,Surabaya,Bandung,Bekasi,Medan,Tangerang,Depok,Semarang,Palembang,South Tangerang".split(","));
			COUNTRIES.put("Brazil", "Brasilia,San Pablo,Rio de Janeiro,Salvador,Fortaleza,Belo Horizonte,Manaus,Curitiba,Recife,Porto Alegre".split(","));
			COUNTRIES.put("Pakistan", "Islamabad,Karachi,Lahore,Faisalabad,Rawalpindi,Gujranwala,Multan,Hyderabad,Peshawar,Quetta".split(","));
			COUNTRIES
					.put("Russia", "Moscow,Saint Petersburg,Novosibirsk,Yekaterinburg,Nizhny Novgorod,Kazan,Chelyabinsk,Samara,Omsk,Rostov-na-Donu".split(","));
			COUNTRIES.put("Japan", "Tokio,Yokohama,Ōsaka,Nagoya,Sapporo,Kōbe,Kyōto,Fukuoka,Kawasaki,Saitama".split(","));
			COUNTRIES.put("Mexico", "Mexico City,Guadalajara,Monterrey,Puebla,Toluca,Tijuana,León,Juárez,Torreón,Querétaro".split(","));
		}
	}

	public static List<Country> getCounties()
	{
		String[] countryNames = new String[COUNTRIES.keySet().size()];

		COUNTRIES.keySet().toArray(countryNames);

		List<Country> list = new ArrayList<Country>();

		for (int i = 0; i < countryNames.length; i++)
		{
			list.add(new Country(i, countryNames[i]));
		}

		return list;
	}

	private static String getRandomString(String[] arr)
	{
		return arr[randomInt(0, arr.length - 1)];
	}

	private static String GetRandomAddress()
	{
		if (Math.random() > 0.9)
			return String.format("%s %s %s %s", randomInt(0, 999), getRandomString(STREET_NAMES), getRandomString(STREET_TYPES),
					getRandomString(STREET_ORIENTATION));
		else
			return String.format("%s %s %s", randomInt(0, 999), getRandomString(STREET_NAMES), getRandomString(STREET_TYPES));
	}

	private static int randomInt(int min, int max)
	{
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public int getCountryId()
	{
		return countryId;
	}

	public void setCountryId(int countryId)
	{
		this.countryId = countryId;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Calendar getLastOrderDate()
	{
		return lastOrderDate;
	}

	public void setLastOrderDate(Calendar lastOrderDate)
	{
		this.lastOrderDate = lastOrderDate;
	}

	public int getOrderCount()
	{
		return orderCount;
	}

	public void setOrderCount(int orderCount)
	{
		this.orderCount = orderCount;
	}

	public double getOrderTotal()
	{
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal)
	{
		this.orderTotal = orderTotal;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	public String getName()
	{
		return String.format("%s %s", firstName, lastName);
	}

}
