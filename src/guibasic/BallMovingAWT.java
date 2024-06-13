package guibasic;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BallMovingAWT {
    public static void main(String[] args) {
		FFrame f = new FFrame();
		f.setSize(500, 500);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.show();
	}


	static class FFrame extends Frame implements Runnable {

		Thread th;
		Ball myBall1;
		Ball myBall2;

		private boolean enable = true;
		private int counter = 0;

		FFrame() {
			th = new Thread(this);
			th.start();
		}

		public void run() {


			myBall1 = new Ball();
			myBall1.setPosition(200, 150);
			myBall1.setR(10);
			myBall1.setColor(Color.RED);

			myBall2 = new Ball();
			myBall2.setPosition(50, 150);
			myBall2.setR(20);
			myBall2.setColor(Color.GREEN);

			while (enable) {

				try {
					th.sleep(100);
					counter++;
					if (counter >= 200) enable = false;
				} catch (InterruptedException e) {
				}


				myBall1.move();
				myBall2.move();

				repaint();  // paint()メソッドが呼び出される
			}
		}


		public void paint(Graphics g) {
			myBall1.draw(g);
			myBall2.draw(g);
		}

		// Ball というインナークラスを作る
		class Ball {
			int x;
			int y;
			int r; // 半径
			Color c;

            boolean isRed;
            boolean isGreen;

			int xDir = 1;  // 1:+方向  -1: -方向
			int yDir = 1;

			void setColor(Color c) {
				this.c = c;
			}


			void move() {

				if ((xDir == 1) && (x >= 500 - r*2)) {
					xDir = -1;
                    toggleColor();
				}
				if ((xDir == -1) && (x <= 0)) {
					xDir = 1;
                    toggleColor();
				}

				if (xDir == 1) {
					x = x + 10;
				} else {
					x = x - 10;
				}


				if ((yDir == 1) && (y >= 500 - r*2)) {
					yDir = -1;
                    toggleColor();
				}
				if ((yDir == -1) && (y <= 0 + r*2)) {
					yDir = 1;
                    toggleColor();
				}

				if (yDir == 1) {
					y = y + 10;
				} else {
					y = y - 10;
				}
			}

            void toggleColor() {
                if (c == Color.RED) {
                    c = Color.GREEN;
                } else {
                    c = Color.RED;
                }
            }


			void setPosition(int x, int y) {
				this.x = x;
				this.y = y;
			}

			void setR(int r) {
				this.r = r;
			}

			void draw(Graphics g) {
				g.setColor(c);
				g.fillOval(x, y, 2 * r, 2 * r);  // rは半径なので2倍にする
			}

		}//innner class Ball end

	}
}
