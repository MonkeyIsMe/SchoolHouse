package com.tongansz.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class CodeAction extends ActionSupport{
	
	public void SendCode() throws IOException {
		ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
	    HttpSession session = request.getSession();
	    String str = code();
	    //System.out.println(str);
	    session.setAttribute("checkCode", str);
	    BufferedImage bi = new BufferedImage(100,33,BufferedImage.TYPE_INT_BGR);
	    Graphics g = bi.getGraphics();
	    g.setFont(new Font(null, Font.ITALIC, 22));
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 0, 100, 33);
	    g.setColor(Color.BLACK);
	    g.drawString(str, 30, 25);
	    response.setContentType("image/jpg");
	    ImageIO.setUseCache(false);
	    ImageIO.write(bi, "jpg", response.getOutputStream());
	}
	
	private String code(){
        StringBuffer stringBuffer = new StringBuffer();
        char [] arr= {'0','1','2','3','4','5','6','7','8','9',
                'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
        for(int i = 0; i < 4; i++){
            stringBuffer.append(arr[new Random().nextInt(arr.length)]);
        }
        return stringBuffer.toString();
    }
	
}


