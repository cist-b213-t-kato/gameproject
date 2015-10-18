/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargame;

//自機と敵機の共通情報を持つクラス

import javafx.scene.canvas.GraphicsContext;

public abstract class Car {

    protected boolean flag;//削除判定に使う
//    protected int count;//カウンタ
    protected double x, y;//座標
    protected double width, height;//幅と高さ

    public boolean isHitting(Car target) {
        if (this.getX() < target.getX() + target.getWidth()
                && target.getX() < this.getX() + this.getWidth()
                && this.getY() < target.getY() + target.getHeight()
                && target.getY() < this.getY() + this.getHeight())
        {
            return true;
        }else{
            return false;
        }


    }

     //以下ゲッター

    public boolean isFlag() {
        return flag;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    //以下セッター

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidth(double w) {
        this.width = w;
    }

    public void setHeight(double h) {
        this.height = h;
    }

    abstract public void graph(GraphicsContext gc);

}
