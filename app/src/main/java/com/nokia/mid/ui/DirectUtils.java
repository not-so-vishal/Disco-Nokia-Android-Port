package com.nokia.mid.ui;
import javax.microedition.lcdui.Graphics;
public final class DirectUtils { public static DirectGraphics getDirectGraphics(Graphics g){return new DirectGraphics(g);} public static javax.microedition.lcdui.Font getFont(int face,int style,int size){return javax.microedition.lcdui.Font.getFont(face,style,size);} }
