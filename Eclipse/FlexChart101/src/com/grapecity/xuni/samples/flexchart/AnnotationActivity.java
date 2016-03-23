package com.grapecity.xuni.samples.flexchart;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;

import com.grapecity.xuni.chartcore.ChartPositionType;
import com.grapecity.xuni.flexchart.ChartAnnotationAttachment;
import com.grapecity.xuni.flexchart.ChartAnnotationPosition;
import com.grapecity.xuni.flexchart.ChartEllipseAnnotation;
import com.grapecity.xuni.flexchart.ChartImageAnnotation;
import com.grapecity.xuni.flexchart.ChartLineAnnotation;
import com.grapecity.xuni.flexchart.ChartPolygonAnnotation;
import com.grapecity.xuni.flexchart.ChartRectangleAnnotation;
import com.grapecity.xuni.flexchart.ChartSeries;
import com.grapecity.xuni.flexchart.ChartType;
import com.grapecity.xuni.flexchart.FlexChart;

public class AnnotationActivity extends Activity
{

	private FlexChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_annotation);
		mChart = (FlexChart) findViewById(R.id.flexchart);
		mChart.getLegend().setPosition(ChartPositionType.NONE);
		mChart.setChartType(ChartType.LINE);
		mChart.setBindingX("date");
		ChartSeries seriesSales = new ChartSeries(mChart, "value", "value");
		mChart.getSeries().add(seriesSales);
		mChart.setItemsSource(getDataSource());
		mChart.getAxisY().setMajorUnit(10d);
		mChart.getAxisX().setMajorUnit(7d);

		ChartPolygonAnnotation polygon = new ChartPolygonAnnotation();
		polygon.getPoints().add(new PointF(150, 50));
		polygon.getPoints().add(new PointF(100, 100));
		polygon.getPoints().add(new PointF(125, 150));
		polygon.getPoints().add(new PointF(180, 150));
		polygon.getPoints().add(new PointF(200, 100));
		polygon.setBorderWidth(1);
		polygon.setBorderColor(Color.rgb(1, 169, 219));
		polygon.setColor(Color.RED);
		polygon.setText("Absolute");
		polygon.setTooltipText("This is polygon annotation. \r\n Attachment : Absolute \r\n Paths: [(300,100),(200,100),(250,300),(360,300),(400,200)]");
		mChart.getAnnotations().add(polygon);

		ChartPolygonAnnotation triangle = new ChartPolygonAnnotation();
		triangle.setAttachment(ChartAnnotationAttachment.DataIndex);
		triangle.setSeriesIndex(0);
		triangle.setPointIndex(10);
		triangle.setOffset(new PointF(0, 30));
		triangle.getPoints().add(new PointF(0, 0));
		triangle.getPoints().add(new PointF(-50, 100));
		triangle.getPoints().add(new PointF(50, 100));
		triangle.setBorderWidth(1);
		triangle.setBorderColor(Color.rgb(1, 169, 219));
		triangle.setColor(Color.YELLOW);
		triangle.setText("DataIndex");
		triangle.setTooltipText("This is polygon annotation. \r\n Attachment : DataIndex \r\n Paths: [(0,0),(-100,200),(100,200)] \r\n Offset: {x:0, y:30}");
		mChart.getAnnotations().add(triangle);

		ChartPolygonAnnotation flag = new ChartPolygonAnnotation();
		flag.setAttachment(ChartAnnotationAttachment.DataIndex);
		flag.setSeriesIndex(0);
		flag.setPointIndex(26);
		flag.getPoints().add(new PointF(0, 0));
		flag.getPoints().add(new PointF(0, -40));
		flag.getPoints().add(new PointF(25, -30));
		flag.getPoints().add(new PointF(2.5f, -20));
		flag.getPoints().add(new PointF(2.5f, 0));
		flag.setBorderWidth(0);
		flag.setColor(Color.RED);
		flag.setTooltipText("This is polygon annotation. \r\n Attachment : DataIndex \r\n Paths: [(0,0),(0,-80),(50,-60),(5,-40),(5,0)] \r\n");
		mChart.getAnnotations().add(flag);

		ChartEllipseAnnotation ellipse = new ChartEllipseAnnotation();
		ellipse.setAttachment(ChartAnnotationAttachment.Relative);
		ellipse.setPoint(new PointF(0.4f, 0.45f));
		ellipse.setWidth(100);
		ellipse.setHeight(60);
		ellipse.setBorderWidth(1);
		ellipse.setBorderColor(Color.rgb(210, 161, 102));
		ellipse.setColor(Color.rgb(245, 188, 120));
		ellipse.setText("Relative");
		ellipse.setTooltipText("This is ellipse annotation. \r\n Attachment : Relative \r\n Point: {x:0.4, y:0.45}");
		mChart.getAnnotations().add(ellipse);

		ChartLineAnnotation line = new ChartLineAnnotation();
		line.setAttachment(ChartAnnotationAttachment.DataCoordinate);
		line.setStart(new PointF(35, 60));
		line.setEnd(new PointF(45, 80));
		line.setLineWidth(3);
		line.setColor(Color.rgb(1, 169, 219));
		line.setTooltipText("This is line annotation.\r\n Attachment : DataCoordinate \r\n Start: {x:35, y:60} End: {X:45, y:80}");
		mChart.getAnnotations().add(line);

		ChartRectangleAnnotation rect = new ChartRectangleAnnotation();
		rect.setAttachment(ChartAnnotationAttachment.DataCoordinate);
		rect.setPoint(new PointF(35, 30));
		rect.setWidth(150);
		rect.setHeight(100);
		rect.setColor(Color.rgb(163, 209, 167));
		rect.setBorderColor(Color.rgb(210, 161, 102));
		rect.setBorderWidth(1);
		rect.setFontSize(18);
		rect.setText("DataCoordinate");
		rect.setTooltipText("This is rectangle annotation.\r\n Attachment : DataCoordinate \r\n Point: {x:35, y:30}");
		mChart.getAnnotations().add(rect);

		ChartRectangleAnnotation left = new ChartRectangleAnnotation();
		left.setAttachment(ChartAnnotationAttachment.DataIndex);
		left.setPointIndex(31);
		left.setSeriesIndex(0);
		left.setWidth(50);
		left.setHeight(25);
		left.setPosition(ChartAnnotationPosition.Left);
		left.setColor(Color.WHITE);
		left.setBorderColor(Color.rgb(210, 161, 102));
		left.setBorderWidth(1);
		left.setFontSize(16);
		left.setText("Left");
		left.setTooltipText("This is rectangle annotation.\r\n Attachment : DataIndex \r\n PointIndex: 31 \r\n Position: Left");
		mChart.getAnnotations().add(left);

		ChartRectangleAnnotation right = new ChartRectangleAnnotation();
		right.setAttachment(ChartAnnotationAttachment.DataIndex);
		right.setPointIndex(31);
		right.setSeriesIndex(0);
		right.setWidth(50);
		right.setHeight(25);
		right.setPosition(ChartAnnotationPosition.Right);
		right.setColor(Color.WHITE);
		right.setBorderColor(Color.rgb(210, 161, 102));
		right.setBorderWidth(1);
		right.setFontSize(16);
		right.setText("Right");
		right.setTooltipText("This is rectangle annotation.\r\n Attachment : DataIndex \r\n PointIndex: 31 \r\n Position: Right");
		mChart.getAnnotations().add(right);

		ChartImageAnnotation image = new ChartImageAnnotation();
		image.setAttachment(ChartAnnotationAttachment.DataCoordinate);
		image.setPoint(new PointF(16, 80));
		image.setWidth(36);
		image.setHeight(36);
		image.setTooltipText("This is image annotation. \r\n Attachment : DataCoordinate \r\n Point: {x:16, y:80}");
		image.setSource(android.graphics.BitmapFactory.decodeResource(this.getResources(), R.drawable.icon));
		mChart.getAnnotations().add(image);
	}

	private List<TestObject> getDataSource()
	{
		List<TestObject> objects = new ArrayList<TestObject>();
		objects.add(new TestObject("1-Jan", 96));
		objects.add(new TestObject("2-Jan", 19));
		objects.add(new TestObject("3-Jan", 54));
		objects.add(new TestObject("4-Jan", 83));
		objects.add(new TestObject("5-Jan", 15));
		objects.add(new TestObject("6-Jan", 56));
		objects.add(new TestObject("7-Jan", 36));
		objects.add(new TestObject("8-Jan", 4));
		objects.add(new TestObject("9-Jan", 29));
		objects.add(new TestObject("10-Jan", 93));
		objects.add(new TestObject("11-Jan", 38));
		objects.add(new TestObject("12-Jan", 71));
		objects.add(new TestObject("13-Jan", 50));
		objects.add(new TestObject("14-Jan", 77));
		objects.add(new TestObject("15-Jan", 69));
		objects.add(new TestObject("16-Jan", 13));
		objects.add(new TestObject("17-Jan", 79));
		objects.add(new TestObject("18-Jan", 57));
		objects.add(new TestObject("19-Jan", 29));
		objects.add(new TestObject("20-Jan", 62));
		objects.add(new TestObject("21-Jan", 4));
		objects.add(new TestObject("22-Jan", 27));
		objects.add(new TestObject("23-Jan", 66));
		objects.add(new TestObject("24-Jan", 96));
		objects.add(new TestObject("25-Jan", 65));
		objects.add(new TestObject("26-Jan", 12));
		objects.add(new TestObject("27-Jan", 52));
		objects.add(new TestObject("28-Jan", 3));
		objects.add(new TestObject("29-Jan", 61));
		objects.add(new TestObject("30-Jan", 48));
		objects.add(new TestObject("31-Jan", 50));
		objects.add(new TestObject("1-Feb", 70));
		objects.add(new TestObject("2-Feb", 39));
		objects.add(new TestObject("3-Feb", 33));
		objects.add(new TestObject("4-Feb", 25));
		objects.add(new TestObject("5-Feb", 49));
		objects.add(new TestObject("6-Feb", 69));
		objects.add(new TestObject("7-Feb", 46));
		objects.add(new TestObject("8-Feb", 44));
		objects.add(new TestObject("9-Feb", 40));
		objects.add(new TestObject("10-Feb", 35));
		objects.add(new TestObject("11-Feb", 72));
		objects.add(new TestObject("12-Feb", 64));
		objects.add(new TestObject("13-Feb", 10));
		objects.add(new TestObject("14-Feb", 66));
		objects.add(new TestObject("15-Feb", 63));
		objects.add(new TestObject("16-Feb", 78));
		objects.add(new TestObject("17-Feb", 19));
		objects.add(new TestObject("18-Feb", 96));
		objects.add(new TestObject("19-Feb", 26));
		return objects;
	}

	class TestObject
	{
		public String date;
		public double value;

		public TestObject(String date, double value)
		{
			this.date = date;
			this.value = value;
		}
	}
}
