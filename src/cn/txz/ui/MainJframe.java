package cn.txz.ui;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainJframe  extends JFrame  implements  KeyListener  {
    JLabel JL_wolf;
    JLabel JL_Sheep1;
    JLabel JL_Sheep2;
    JLabel JL_Sheep3;
    int num=0; //几个羊进了笼子
    int total=3; //笼子总数
    Map m=new Map();
     public MainJframe(){
         this.setSize(810,650);
         this.setLocationRelativeTo(null);
         this.setVisible(true);
         this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         Longzi();
         Wolf();
         Sheep();
         //障碍
         tree();
         SetBack();
         this.addKeyListener(this);
     }
     private void SetBack(){
         ImageIcon i=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\floor.png");
         JLabel jl=new JLabel(i);
         jl.setBounds(0,0,800,600);
         JPanel j=(JPanel)this.getContentPane();
         j.setOpaque(false);
         this.getLayeredPane().add(jl, new Integer(Integer.MIN_VALUE));
     }
     private  void Wolf(){
         wx=1;
         wy=1;
         ImageIcon i=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\zhengmian.png");
          JL_wolf=new JLabel(i);
         JL_wolf.setBounds(wx*50,wy*50,50,50);
         this.add(JL_wolf);
    }
    private void Sheep(){
         ImageIcon i=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\4.png");
         JL_Sheep1=new JLabel(i);
         JL_Sheep1.setBounds(300,150,50,50);
         this.add(JL_Sheep1);
         map[3][6]=4;
         sheeps[3][6]=JL_Sheep1;

         JL_Sheep2=new JLabel(i);
         JL_Sheep2.setBounds(300,250,50,50);
         this.add(JL_Sheep2);
         map[5][6]=4;
         sheeps[5][6]=JL_Sheep2;

         JL_Sheep3=new JLabel(i);
         JL_Sheep3.setBounds(300,350,50,50);
         this.add(JL_Sheep3);
         map[7][6]=4;
         sheeps[7][6]=JL_Sheep3;
    }
    private void Longzi(){
          ImageIcon i=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\longzi.png");
        JLabel JL_long1=new JLabel(i);
        JL_long1.setBounds(700,300,50,50);
        this.add(JL_long1);
        map[6][14]=8;

        JLabel JL_long2=new JLabel(i);
        JL_long2.setBounds(700,350,50,50);
        this.add(JL_long2);
        map[7][14]=8;

        JLabel JL_long3=new JLabel(i);
        JL_long3.setBounds(700,400,50,50);
        this.add(JL_long3);
        map[8][14]=8;

        JLabel jl_long4=new JLabel(i);
        jl_long4.setBounds(150,300,50,50);
        this.add(jl_long4);
        map[6][3]=8;


    }
    //JLable 储存数组
    JLabel [][] sheeps=new JLabel[12][16];
    //1是障碍 0是空地 4是箱子
     int map[][]={
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
     //代表人物
     int wx;
     int wy;
    private void tree(){
        ImageIcon tree=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\1.png");
         for(int i=0;i<map.length;i++)
             for(int j=0;j<map[i].length;j++)
             {
                 if(map[i][j]==1)
                 {
                     JLabel JL_tree = new JLabel(tree);
                     JL_tree.setBounds(50 * j, 50 * i, 50, 50);
                     this.add(JL_tree);
                 }
             }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_S)
        {
            //遇到41
            if(map[wx+1][wy]==4&&map[wx+2][wy]==1) {
                return;
            }
            if(map[wx+1][wy]==4&&map[wx+2][wy]==4){
                return;
            }
            if(map[wx+1][wy]==4&&map[wx+2][wy]==0){
                //羊动再狼动
                map[wx + 1][wy] = 0;
                map[wx + 2][wy] = 4;
                //羊移动
                System.out.println(wx+" "+wy);
                sheeps[wx + 1][wy].setLocation(wy * 50, wx * 50 + 100);
                sheeps[wx + 2][wy] = sheeps[wx + 1][wy];
                sheeps[wx + 1][wy] = null;
                //狼移动代码
                int x = (int) JL_wolf.getLocation().getX();
                int y = (int) JL_wolf.getLocation().getY();
                JL_wolf.setLocation(x, y + 50);
                ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\zhengmian.png");
                JL_wolf.setIcon(i);
                wx = wx + 1;
            }
            if(map[wx+1][wy]==4&&map[wx+2][wy]==8)
            {
                //羊先动（笼子羊变成12 换图片）狼再动
                //羊移动代码
                map[wx+1][wy]=0;
                map[wx+2][wy]=12;
                ImageIcon b=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\cry.png");
                sheeps[wx+1][wy].setIcon(b);
                sheeps[wx+1][wy].setLocation(wy*50,wx*50+100);
                sheeps[wx+2][wy]=sheeps[wx+1][wy];
                sheeps[wx+1][wy]=null;
                //狼移动代码
                int x = (int) JL_wolf.getLocation().getX();
                int y = (int) JL_wolf.getLocation().getY();
                JL_wolf.setLocation(x, y + 50);
                ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\zhengmian.png");
                JL_wolf.setIcon(i);
                wx = wx + 1;
            }
            if(map[wx+1][wy]==4&&map[wx+2][wy]==12)
            {
                return;
            }
            if(map[wx+1][wy]==12&&map[wx+2][wy]==1)
            {
                return;
            }
            if(map[wx+1][wy]==12&&map[wx+2][wy]==4)
            {
                return;
            }
            if(map[wx+1][wy]==12&&map[wx+2][wy]==8)
            {
                //羊先动，狼再动，图片不变（待定）
                //羊移动代码
                map[wx+1][wy]=8;
                map[wx+2][wy]=12;
                sheeps[wx+1][wy].setLocation(wy*50,wx*50+100);
                sheeps[wx+2][wy]=sheeps[wx+1][wy];
                sheeps[wx+1][wy]=null;
                //狼移动代码
                int x = (int) JL_wolf.getLocation().getX();
                int y = (int) JL_wolf.getLocation().getY();
                JL_wolf.setLocation(x, y + 50);
                ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\zhengmian.png");
                JL_wolf.setIcon(i);
                wx = wx + 1;
            }
            if(map[wx+1][wy]==12&&map[wx+2][wy]==0)
            {
                //也就是出牢 羊先动，狼再动（换图片）
                //羊移动代码
                map[wx+1][wy]=8;
                map[wx+2][wy]=4;
                ImageIcon b=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\4.png");
                sheeps[wx+1][wy].setIcon(b);
                sheeps[wx+1][wy].setLocation(wy*50,wx*50+100);
                sheeps[wx+2][wy]=sheeps[wx+1][wy];
                sheeps[wx+1][wy]=null;
                //狼移动代码
                int x = (int) JL_wolf.getLocation().getX();
                int y = (int) JL_wolf.getLocation().getY();
                JL_wolf.setLocation(x, y + 50);
                ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\zhengmian.png");
                JL_wolf.setIcon(i);
                wx = wx + 1;
            }
            if(map[wx+1][wy]==12&&map[wx+2][wy]==12) {
                return;
            }
            if(map[wx+1][wy]==1)
            {
                return;
            }
            if(map[wx+1][wy]==0)
            {
                //狼移动代码
                int x = (int) JL_wolf.getLocation().getX();
                int y = (int) JL_wolf.getLocation().getY();
                JL_wolf.setLocation(x, y + 50);
                ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\zhengmian.png");
                JL_wolf.setIcon(i);
                wx = wx + 1;
            }
            if(map[wx+1][wy]==8)
            {

                int y = (int) JL_wolf.getLocation().getY();
                int x = (int) JL_wolf.getLocation().getX();
                JL_wolf.setLocation(x, y + 50);
                ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\zhengmian.png");
                JL_wolf.setIcon(i);
                wx=wx+1;
            }
        }

         if(e.getKeyCode()==KeyEvent.VK_D)
         {
             if(map[wx][wy+1]==4&&map[wx][wy+2]==1)
             {
                 return;
             }
             if(map[wx][wy+1]==4&&map[wx][wy+2]==4)
             {
                 return;
             }
             if(map[wx][wy+1]==4&&map[wx][wy+2]==12)
             {
                 return;
             }
             if(map[wx][wy+1]==4&&map[wx][wy+2]==8)
             {
                 //变成羊牢，换图片，羊
                 //羊移动代码
                 map[wx][wy+1]=0;
                 map[wx][wy+2]=12;
                 ImageIcon b=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\cry.png");
                 sheeps[wx][wy+1].setIcon(b);
                 sheeps[wx][wy+1].setLocation(wy*50+100,wx*50);
                 sheeps[wx][wy+2]=sheeps[wx][wy+1];
                 sheeps[wx][wy+1]=null;
                 //狼移动代码
                 int x = (int) JL_wolf.getLocation().getX();
                 int y = (int) JL_wolf.getLocation().getY();
                 JL_wolf.setLocation(x + 50, y);
                 ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\0-1.png");
                 JL_wolf.setIcon(i);
                 wy = wy + 1;
             }
             if(map[wx][wy+1]==4&&map[wx][wy+2]==0)
             {
                 //羊动再狼动
                 //羊移动代码
                 map[wx][wy+1] = 0;
                 map[wx][wy+2] = 4;
                 sheeps[wx][wy+1].setLocation(wy * 50+100, wx * 50);
                 sheeps[wx][wy+2] = sheeps[wx][wy+1];
                 sheeps[wx + 1][wy] = null;
                 //狼移动代码
                 int x = (int) JL_wolf.getLocation().getX();
                 int y = (int) JL_wolf.getLocation().getY();
                 JL_wolf.setLocation(x + 50, y);
                 ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\0-1.png");
                 JL_wolf.setIcon(i);
                 wy = wy + 1;
             }
             if(map[wx][wy+1]==12&map[wx][wy+2]==1)
             {
                 return;
             }
             if(map[wx][wy+1]==12&&map[wx][wy+2]==4)
             {
                 return;
             }
             if(map[wx][wy+1]==12&&map[wx][wy+2]==12)
             {
                 return;
             }
             if(map[wx][wy+1]==12&&map[wx][wy+2]==8)
             {
                 //图片不变，羊先动，狼后动
                 map[wx][wy+1]=8;
                 map[wx][wy+2]=12;
                 sheeps[wx][wy+1].setLocation(wy*50+100,wx*50);
                 sheeps[wx][wy+2]=sheeps[wx][wy+1];
                 sheeps[wx][wy+1]=null;
                 //狼移动代码
                 int x = (int) JL_wolf.getLocation().getX();
                 int y = (int) JL_wolf.getLocation().getY();
                 JL_wolf.setLocation(x + 50, y);
                 ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\0-1.png");
                 JL_wolf.setIcon(i);
                 wy = wy + 1;
             }
             if(map[wx][wy+1]==12&&map[wx][wy+2]==0)
             {
                 //出牢 换图片
                 map[wx][wy+1]=8;
                 map[wx][wy+2]=4;
                 ImageIcon b=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\4.png");
                 sheeps[wx][wy+1].setIcon(b);
                 sheeps[wx][wy+1].setLocation(wy*50+100,wx*50);
                 sheeps[wx][wy+2]=sheeps[wx][wy+1];
                 sheeps[wx][wy+1]=null;
                 //狼移动代码
                 int x = (int) JL_wolf.getLocation().getX();
                 int y = (int) JL_wolf.getLocation().getY();
                 JL_wolf.setLocation(x + 50, y);
                 ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\0-1.png");
                 JL_wolf.setIcon(i);
                 wy = wy + 1;
             }
             if(map[wx][wy+1]==1)
             {
                 return;
             }
             if(map[wx][wy+1]==0)
             {
                 //狼移动代码
                 int x = (int) JL_wolf.getLocation().getX();
                 int y = (int) JL_wolf.getLocation().getY();
                 JL_wolf.setLocation(x + 50, y);
                 ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\0-1.png");
                 JL_wolf.setIcon(i);
                 wy = wy + 1;
             }
             if(map[wx][wy+1]==8)
             {
                 //狼移动代码
                 int y = (int) JL_wolf.getLocation().getY();
                 int x = (int) JL_wolf.getLocation().getX();
                 JL_wolf.setLocation(x + 50, y);
                 ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\0-1.png");
                 JL_wolf.setIcon(i);
                 wy = wy + 1;
             }
         }

          if(e.getKeyCode()==KeyEvent.VK_A)
          {
              if(map[wx][wy-1]==4&&map[wx][wy-2]==1)
              {
                  return;
              }
              if(map[wx][wy-1]==4&&map[wx][wy-2]==4)
              {
                  return;
              }
              if(map[wx][wy-1]==4&&map[wx][wy-2]==12)
              {
                  return;
              }
              if(map[wx][wy-1]==4&&map[wx][wy-2]==8)
              {
                  //变成羊牢，换图片，羊
                  //羊移动代码
                  map[wx][wy-1]=0;
                  map[wx][wy-2]=12;
                  ImageIcon b=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\cry.png");
                  sheeps[wx][wy-1].setIcon(b);
                  sheeps[wx][wy-1].setLocation(wy*50-100,wx*50);
                  sheeps[wx][wy-2]=sheeps[wx][wy-1];
                  sheeps[wx][wy-1]=null;
                  //狼移动代码
                  int x = (int) JL_wolf.getLocation().getX();
                  int y = (int) JL_wolf.getLocation().getY();
                  JL_wolf.setLocation(x - 50, y);
                  ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\01.png");
                  JL_wolf.setIcon(i);
                  wy = wy - 1;
              }
              if(map[wx][wy-1]==4&&map[wx][wy-2]==0)
              {
                  //羊动再狼动
                  //羊移动代码
                  map[wx][wy-1] = 0;
                  map[wx][wy-2] = 4;
                  sheeps[wx][wy-1].setLocation(wy * 50-100, wx * 50);
                  sheeps[wx][wy-2] = sheeps[wx][wy-1];
                  sheeps[wx -1][wy] = null;
                  //狼移动代码
                  int x = (int) JL_wolf.getLocation().getX();
                  int y = (int) JL_wolf.getLocation().getY();
                  JL_wolf.setLocation(x - 50, y);
                  ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\01.png");
                  JL_wolf.setIcon(i);
                  wy = wy - 1;
              }
              if(map[wx][wy-1]==12&map[wx][wy-2]==1)
              {
                  return;
              }
              if(map[wx][wy-1]==12&&map[wx][wy-2]==4)
              {
                  return;
              }
              if(map[wx][wy-1]==12&&map[wx][wy-2]==12)
              {
                  return;
              }
              if(map[wx][wy-1]==12&&map[wx][wy-2]==8)
              {
                  //图片不变，羊先动，狼后动
                  map[wx][wy-1]=8;
                  map[wx][wy-2]=12;
                  sheeps[wx][wy-1].setLocation(wy*50-100,wx*50);
                  sheeps[wx][wy-2]=sheeps[wx][wy-1];
                  sheeps[wx][wy-1]=null;
                  //狼移动代码
                  int x = (int) JL_wolf.getLocation().getX();
                  int y = (int) JL_wolf.getLocation().getY();
                  JL_wolf.setLocation(x - 50, y);
                  ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\01.png");
                  JL_wolf.setIcon(i);
                  wy = wy - 1;
              }
              if(map[wx][wy-1]==12&&map[wx][wy-2]==0)
              {
                  //出牢 换图片
                  map[wx][wy-1]=8;
                  map[wx][wy-2]=4;
                  ImageIcon b=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\4.png");
                  sheeps[wx][wy-1].setIcon(b);
                  sheeps[wx][wy-1].setLocation(wy*50-100,wx*50);
                  sheeps[wx][wy-2]=sheeps[wx][wy-1];
                  sheeps[wx][wy-1]=null;
                  //狼移动代码
                  int x = (int) JL_wolf.getLocation().getX();
                  int y = (int) JL_wolf.getLocation().getY();
                  JL_wolf.setLocation(x - 50, y);
                  ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\01.png");
                  JL_wolf.setIcon(i);
                  wy = wy - 1;
              }
              if(map[wx][wy-1]==1)
              {
                  return;
              }
              if(map[wx][wy-1]==0)
              {
                  //狼移动代码
                  int y = (int) JL_wolf.getLocation().getY();
                  int x = (int) JL_wolf.getLocation().getX();
                  JL_wolf.setLocation(x - 50, y);
                  ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\01.png");
                  JL_wolf.setIcon(i);
                  wy = wy - 1;
              }
              if(map[wx][wy-1]==8) {
                  //狼移动代码
                  int x = (int) JL_wolf.getLocation().getX();
                  int y = (int) JL_wolf.getLocation().getY();
                  JL_wolf.setLocation(x - 50, y);
                  ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\01.png");
                  JL_wolf.setIcon(i);
                  wy = wy - 1;
              }
          }

           if(e.getKeyCode()==KeyEvent.VK_W)
           {
               //遇到41
               if(map[wx-1][wy]==4&&map[wx-2][wy]==1) {
                   return;
               }
               if(map[wx-1][wy]==4&&map[wx-2][wy]==4){
                   return;
               }
               if(map[wx-1][wy]==4&&map[wx-2][wy]==0){
                   //羊动再狼动
                   map[wx -1][wy] = 0;
                   map[wx - 2][wy] = 4;
                   //羊移动
                   System.out.println(wx+" "+wy);
                   sheeps[wx - 1][wy].setLocation(wy * 50, wx * 50 - 100);
                   sheeps[wx - 2][wy] = sheeps[wx - 1][wy];
                   sheeps[wx - 1][wy] = null;
                   //狼移动代码
                   int y = (int) JL_wolf.getLocation().getY();
                   int x = (int) JL_wolf.getLocation().getX();
                   JL_wolf.setLocation(x, y - 50);
                   ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\10.png");
                   JL_wolf.setIcon(i);
                   wx = wx - 1;
               }
               if(map[wx-1][wy]==4&&map[wx-2][wy]==8)
               {
                   //羊先动（笼子羊变成12 换图片）狼再动
                   //羊移动代码
                   map[wx-1][wy]=0;
                   map[wx-2][wy]=12;
                   ImageIcon b=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\cry.png");
                   sheeps[wx-1][wy].setIcon(b);
                   sheeps[wx-1][wy].setLocation(wy*50,wx-100);
                   sheeps[wx-2][wy]=sheeps[wx-1][wy];
                   sheeps[wx-1][wy]=null;
                   //狼移动代码
                   int y = (int) JL_wolf.getLocation().getY();
                   int x = (int) JL_wolf.getLocation().getX();
                   JL_wolf.setLocation(x, y - 50);
                   ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\10.png");
                   JL_wolf.setIcon(i);
                   wx = wx - 1;
               }
               if(map[wx-1][wy]==4&&map[wx-2][wy]==12)
               {
                   return;
               }
               if(map[wx-1][wy]==12&&map[wx-2][wy]==1)
               {
                   return;
               }
               if(map[wx-1][wy]==12&&map[wx-2][wy]==4)
               {
                   return;
               }
               if(map[wx-1][wy]==12&&map[wx-2][wy]==8)
               {
                   //羊先动，狼再动，图片不变（待定）
                   //羊移动代码
                   map[wx-1][wy]=8;
                   map[wx-2][wy]=12;
                   sheeps[wx-1][wy].setLocation(wy*50,wx*50-100);
                   sheeps[wx-2][wy]=sheeps[wx-1][wy];
                   sheeps[wx-1][wy]=null;
                   //狼移动代码
                   int y = (int) JL_wolf.getLocation().getY();
                   int x = (int) JL_wolf.getLocation().getX();
                   JL_wolf.setLocation(x, y - 50);
                   ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\10.png");
                   JL_wolf.setIcon(i);
                   wx = wx - 1;
               }
               if(map[wx-1][wy]==12&&map[wx-2][wy]==0)
               {
                   //也就是出牢 羊先动，狼再动（换图片）
                   //羊移动代码
                   map[wx-1][wy]=8;
                   map[wx-2][wy]=4;
                   ImageIcon b=new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\4.png");
                   sheeps[wx-1][wy].setIcon(b);
                   sheeps[wx-1][wy].setLocation(wy*50,wx*50-100);
                   sheeps[wx-2][wy]=sheeps[wx-1][wy];
                   sheeps[wx-1][wy]=null;
                   //狼移动代码
                   int y = (int) JL_wolf.getLocation().getY();
                   int x = (int) JL_wolf.getLocation().getX();
                   JL_wolf.setLocation(x, y - 50);
                   ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\10.png");
                   JL_wolf.setIcon(i);
                   wx = wx - 1;
               }
               if(map[wx-1][wy]==12&&map[wx-2][wy]==12) {
                   return;
               }
               if(map[wx-1][wy]==1)
               {
                   return;
               }
               if(map[wx-1][wy]==0)
               {
                   //狼移动代码
                   int y = (int) JL_wolf.getLocation().getY();
                   int x = (int) JL_wolf.getLocation().getX();
                   JL_wolf.setLocation(x, y - 50);
                   ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\10.png");
                   JL_wolf.setIcon(i);
                   wx = wx - 1;
               }
               if(map[wx-1][wy]==8)
               {

                   int x = (int) JL_wolf.getLocation().getX();
                   int y = (int) JL_wolf.getLocation().getY();
                   JL_wolf.setLocation(x, y - 50);
                   ImageIcon i = new ImageIcon("D:\\Myjavawork\\推箱子\\src\\img\\10.png");
                   JL_wolf.setIcon(i);
                   wx = wx - 1;
               }
           }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}



