package com.yang.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Random;

public class ValicodeUtil {
	private static final char valicode[]= { '0', '1', '3', '4', '5', '6', '7', '8', '9',
			'a', 'b', 'c', 'b', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
			'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
	        'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	//随机数对象
	private  static final Random random=new Random();

    
  //随机验证码,位数是codenum位
  //随机5位验证码
  	public char[] RandomCode()
  	{	
  		char[] captcha=new char[5];
  		for(int i=0;i<5;i++)
  		{
  		captcha[i]=valicode[random.nextInt(valicode.length)];		
  		}
  	return captcha;		
  	}
	    
	  //随机颜色
	  	private  Color color() 
	  	{
	  		return new Color(random.nextInt(255),random.nextInt(255) , random.nextInt(255));
	  	}
  	
       // 返回某颜色的反色
      private static Color getReverseColor(Color c)
      {
          return new Color(255 - c.getRed(), 255 - c.getGreen(),
                  255 - c.getBlue());
      }
      
      //画验证码
      public RenderedImage getCaptcha(char[]captcha,int size,int width,int height)
      {	   	
      	//构建一张图片
      	BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      	//创建画布
      	Graphics2D graphics=img.createGraphics();
      	//字体颜色
      	Color c=color();
      	//干扰线颜色
      	Color rc=getReverseColor(c);
      	//设置字体
      	graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, size));
      	//设置矩形颜色
      	graphics.setColor(Color.WHITE);
      	//填充一个矩形
      	graphics.fillRect(0, 0, width, height);
      	//设置字体颜色
      	graphics.setColor(c);
      	for(int i=1;i<=5;i++)
      	{
          //随机旋转角度为0-80度
      	double r=(double)random.nextInt(80);
      	//旋转画布
      	graphics.rotate(r/180*Math.PI, 30*i, 30);//弧度=角度/180*Math.PI，角度=弧度/PI*180
          //设置画布文字
          graphics.drawString(captcha[i-1]+"", 30*i, 30);
      	//将画布旋转回来
      	graphics.rotate(-r/180*Math.PI, 30*i, 30);
      	}
      	//设置干扰线颜色
      	//graphics.setColor(rc);
      	//画干扰线
      	//for(int i=0;i<10;i++)
      	//{
      	//graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
      	//}
      	//设置干扰字符
      	for(int i=0;i<50;i++)
      	{
      		c=color();
      		graphics.setFont(new Font("黑体",Font.BOLD,10));
      		graphics.setColor(c);
      		graphics.drawChars(valicode, random.nextInt(valicode.length), 1, random.nextInt(width), random.nextInt(height));
      	}
      	//绘制矩形
      	graphics.drawRect(0, 0, width, height);
      	return img;
      }
}
