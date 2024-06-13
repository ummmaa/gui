package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacesAWT {
    public static void main(String[] args) {
        new FacesAWT();
    }

    FacesAWT() {
        FaceFrame f = new FaceFrame();
        f.setSize(800, 800);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    // インナークラスを定義
    class FaceFrame extends Frame {

        private FaceObj[] fObjs;

        FaceFrame() {
            FaceObj[] fobjs = new FaceObj[9];
        }

        public void paint(Graphics g) {
            // この中には、g.drawLine というのは入ってこない
            // Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す

            FaceObj[] fobjs = new FaceObj[9];
            for (int j = 0; j < 3; j++) {

                for (int i = 0; i < 3; i++) {
                    fobjs[i + 3 * j] = new FaceObj();
                    fobjs[i + 3 * j].setPosition(200 * i + 50, 200 * j + 50);
                    fobjs[i + 3 * j].setEmotionLevel(i*20,j*10);
                    fobjs[i + 3 * j].drawFace(g);
                }
            }
        }

    }// FaceFrame end

    // Faceクラスを作ってみよう。
    private class FaceObj extends Frame {

        private int w;
        private int h;
        private int xStart;
        private int yStart;
        private int emotionI;
        private int emotionJ;

        public void drawFace(Graphics g) {
            w = 200;
            h = 200;

            drawRim(g);
            g.setColor(Color.BLUE);
            drawBrow(g, 30);
            drawEye(g, 35);
            drawNose(g, 40);
            drawMouth(g, 100);

        }

        public void setPosition(int x, int y){
            this.xStart = x;
            this.yStart = y;
        }

        public void setEmotionLevel(int i, int j){
            this.emotionI = i;
            this.emotionJ = j;
        }

        public void drawRim(Graphics g) { // wが横幅、hが縦幅
            Color color= new Color(255,123,24,56);
            g.setColor(color);
            g.fillRoundRect(xStart+5,yStart+5,w-10,h-10,40,40);
        }

        public void drawBrow(Graphics g, int bx) { // xは目の幅 呼ばれる方(=定義する方)
            g.drawLine(xStart + 45, yStart + 20+ emotionI, xStart + 45 + bx, yStart + 40);
            g.drawLine(xStart + w - 45, yStart + 20 + emotionI, xStart + w - 45 - bx, yStart + 40);
        }

        public void drawNose(Graphics g, int nx) { // xは鼻の長さ
            g.drawLine(xStart + w / 2, yStart + emotionI/2 + h / 2, xStart + w / 2, yStart - 30 + emotionI + h / 2 + nx);
        }

        public void drawEye(Graphics g, int r) { // rは目の半径
            g.drawOval(xStart + 45, yStart + 65, r, r);
            g.drawOval(xStart + w - r - 45, yStart + 65, r, r);
        }

        public void drawMouth(Graphics g, int mx) { // xは口の幅
            int xMiddle = xStart + w / 2;
            int yMiddle = yStart + h - 30;
            g.drawLine(xMiddle - mx / 2, yMiddle, xMiddle, yMiddle - 10 + emotionJ);
            g.drawLine(xMiddle + mx / 2, yMiddle, xMiddle, yMiddle - 10 + emotionJ);
        }
    }
}
